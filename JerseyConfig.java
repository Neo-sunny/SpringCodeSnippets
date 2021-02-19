package com.zyme.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(JerseyConfig.class);
	public JerseyConfig() {
        
    	// scan the resources package for our resources
        register(com.zyme.config.web.service.ConfigRestService.class);
        register(com.zyme.config.RequestInterceptor.class);
        register(com.zyme.config.model.exception.ConfigExceptionMapper.class);
        register(com.zyme.config.controller.ConfigSetupController.class);
        register(org.glassfish.jersey.media.multipart.MultiPartFeature.class);
        LOGGER.info("Package Name::: "+getClass().getPackage().getName() );	
        
        
    }
}
