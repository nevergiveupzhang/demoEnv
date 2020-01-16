package com.example.demo.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpUtils {

	private static RequestConfig requestConfig = null;

	static {
		// 设置请求和传输超时时间
		requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
	}

	/**
	 * post请求传输json参数
	 * 
	 * @param url  url地址
	 * @param json 参数
	 * @return
	 * @throws IOException 
	 */
	public static String httpPost4Tgt(String url, String param) throws IOException {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setProtocolVersion(HttpVersion.HTTP_1_0);
		// 设置请求和传输超时时间
		httpPost.setConfig(requestConfig);
		try {
			if (null != param) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(param, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/x-www-form-urlencoded");
				httpPost.setEntity(entity);
			}
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			CloseableHttpResponse result = httpClient.execute(httpPost);
			Header location=result.getFirstHeader("Location");
			System.out.println(EntityUtils.toString(result.getEntity(), "utf-8"));
			if(location!=null) {
				return location.toString().substring(location.toString().lastIndexOf("/"));
			}
			return null;
		} catch (IOException e) {
			throw e;
		} finally {
			httpPost.releaseConnection();
		}
	}


	public static String httpPost4St(String url, String param) throws IOException {
		// post请求返回结果
				CloseableHttpClient httpClient = HttpClients.createDefault();
				HttpPost httpPost = new HttpPost(url);
				httpPost.setProtocolVersion(HttpVersion.HTTP_1_0);
				// 设置请求和传输超时时间
				httpPost.setConfig(requestConfig);
				try {
					if (null != param) {
						// 解决中文乱码问题
						StringEntity entity = new StringEntity(param, "utf-8");
						entity.setContentEncoding("UTF-8");
						entity.setContentType("application/x-www-form-urlencoded");
						httpPost.setEntity(entity);
					}
					httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
					CloseableHttpResponse result = httpClient.execute(httpPost);
					
					return EntityUtils.toString(result.getEntity(), "utf-8");
				} catch (IOException e) {
					throw e;
				} finally {
					httpPost.releaseConnection();
				}
	}

}
