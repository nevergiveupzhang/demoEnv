package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jasig.cas.client.validation.AssertionImpl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.HttpUtils;

public class LinkFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(request.getParameter("ticket")!=null){
			chain.doFilter(request, response);
			return;
		}
		String url="http://localhost:8219/casServerDemo/v1/tickets/";
		String tgtRes=HttpUtils.httpPost4Tgt(url, "username=test&password=123");
		System.out.println(tgtRes);
		String st=HttpUtils.httpPost4St(url+tgtRes,"service=http://localhost:8219/casClientDemo/");
		System.out.println(st);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
