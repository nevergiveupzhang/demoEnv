package com.example.demo.filter;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;
import org.jasig.cas.client.util.XmlUtils;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.AssertionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.example.demo.utils.HttpUtils;

public class LinkFilter implements Filter {
	private static final String CONST_CAS_ASSERTION = "_const_cas_assertion_";
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		for(Cookie cookie:request.getCookies()) {
			System.out.println("domain="+cookie.getDomain()+"&path="+cookie.getPath()+"&name="+cookie.getName()+"&value="+cookie.getValue());
		}
		String signature=request.getParameter("signature");
		if(StringUtils.isBlank(signature)) {
			chain.doFilter(request, response);
			return;
		}
		if (request.getParameter("ticket") != null) {
			chain.doFilter(request, response);
			return;
		}
		String ticketsUrl = "http://localhost:8219/casServerDemo/v1/tickets/";
		String validateUrl = "http://localhost:8219/casServerDemo/p3/serviceValidate";
		String tgtRes = HttpUtils.httpPost4Tgt(ticketsUrl, "username=test&password="+signature);
		System.out.println(tgtRes);
		Cookie cookie=new Cookie("TGC", tgtRes);
		cookie.setDomain("localhost");
		cookie.setPath("/");
		response.addCookie(cookie);
		String st = HttpUtils.httpPost(ticketsUrl + tgtRes, "service=http://localhost:8219/casClientDemo/");
		System.out.println(st);
		request.setAttribute("ticket", st);
		String validateResult = HttpUtils
				.httpGet(validateUrl + "?service=http://localhost:8219/casClientDemo/&ticket=" + st);
		System.out.println(validateResult);
		final String principal = XmlUtils.getTextForElement(validateResult, "user");
		final Assertion assertion;
		final Map<String, Object> attributes = extractCustomAttributes(validateResult);
		assertion = new AssertionImpl(new AttributePrincipalImpl(principal, attributes));
		request.setAttribute(CONST_CAS_ASSERTION, assertion);
		request.getSession().setAttribute(CONST_CAS_ASSERTION, assertion);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

	protected Map<String, Object> extractCustomAttributes(final String xml) {
		final SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		spf.setValidating(false);
		try {
			final SAXParser saxParser = spf.newSAXParser();
			final XMLReader xmlReader = saxParser.getXMLReader();
			final CustomAttributeHandler handler = new CustomAttributeHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(new StringReader(xml)));
			return handler.getAttributes();
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
			return Collections.emptyMap();
		}
	}

	private class CustomAttributeHandler extends DefaultHandler {

		private Map<String, Object> attributes;

		private boolean foundAttributes;

		private String currentAttribute;

		private StringBuilder value;

		@Override
		public void startDocument() throws SAXException {
			this.attributes = new HashMap<String, Object>();
		}

		@Override
		public void startElement(final String namespaceURI, final String localName, final String qName,
				final Attributes attributes) throws SAXException {
			if ("attributes".equals(localName)) {
				this.foundAttributes = true;
			} else if (this.foundAttributes) {
				this.value = new StringBuilder();
				this.currentAttribute = localName;
			}
		}

		@Override
		public void characters(final char[] chars, final int start, final int length) throws SAXException {
			if (this.currentAttribute != null) {
				value.append(chars, start, length);
			}
		}

		@Override
		public void endElement(final String namespaceURI, final String localName, final String qName)
				throws SAXException {
			if ("attributes".equals(localName)) {
				this.foundAttributes = false;
				this.currentAttribute = null;
			} else if (this.foundAttributes) {
				final Object o = this.attributes.get(this.currentAttribute);

				if (o == null) {
					this.attributes.put(this.currentAttribute, this.value.toString());
				} else {
					final List<Object> items;
					if (o instanceof List) {
						items = (List<Object>) o;
					} else {
						items = new LinkedList<Object>();
						items.add(o);
						this.attributes.put(this.currentAttribute, items);
					}
					items.add(this.value.toString());
				}
			}
		}

		public Map<String, Object> getAttributes() {
			return this.attributes;
		}
	}
}
