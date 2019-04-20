package com.enrico.microservices.got.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return new RestTemplate(getClientHttpRequestFactory());
//
//	}
//
//	private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		OAuth2Authentication oauth = (OAuth2Authentication) securityContext.getAuthentication();
//		String token = (String) oauth.getCredentials();
//
//		Header header = new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
//		List<Header> headers = Lists.newArrayList(header);
//
//		HttpClient client = HttpClients.custom().setDefaultHeaders(headers).build();
//
//		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//
//		clientHttpRequestFactory.setHttpClient(client);
//
//		return clientHttpRequestFactory;
//	}
}
