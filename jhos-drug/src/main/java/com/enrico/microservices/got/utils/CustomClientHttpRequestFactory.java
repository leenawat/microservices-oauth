package com.enrico.microservices.got.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.Lists;

public class CustomClientHttpRequestFactory {

	public static HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory(HttpServletRequest req) {

		String token = req.getHeader("authorization");

		Header header = new BasicHeader("authorization", token);
		List<Header> headers = Lists.newArrayList(header);

		HttpClient client = HttpClients.custom().setDefaultHeaders(headers).build();

		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();

		clientHttpRequestFactory.setHttpClient(client);

		return clientHttpRequestFactory;
	}
	
	public static HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String token = request.getHeader("authorization");

		Header header = new BasicHeader("authorization", token);
		List<Header> headers = Lists.newArrayList(header);

		HttpClient client = HttpClients.custom().setDefaultHeaders(headers).build();

		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();

		clientHttpRequestFactory.setHttpClient(client);

		return clientHttpRequestFactory;
	}
}
