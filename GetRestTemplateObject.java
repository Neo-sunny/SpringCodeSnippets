package com.zyme.config.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GetRestTemplateObject {

	@Value("${app.getmfgid}")
	public String mfgIdAPI;
	
	@Value("${crp.refresh.api}")
	public String refreshApi;
	
	public  String createMfgURL(String clientName) {
		return mfgIdAPI+"?name="+clientName;
	}
	
	public  String createRefreshURL(String clientName) {
		return refreshApi+"&tid="+clientName;
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
}
