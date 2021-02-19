package com.zyme.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.zyme.config.model.constants.ConfigConstants;
import com.zyme.config.persistence.model.entity.ConfigExceptionDTO;

@Provider
public class RequestInterceptor implements ContainerRequestFilter {

	 @Context
	 private HttpServletRequest      request;
	 
	   
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Enumeration<String> e= request.getParameterNames();
		while(e.hasMoreElements()) {
			String param = (String) e.nextElement();
			String value = request.getParameter(param);
			if(value.strip().isBlank() || value.equalsIgnoreCase("Null")) {
				ConfigExceptionDTO exceptionDTO = new ConfigExceptionDTO();
				exceptionDTO.setErrorCode(ConfigConstants.RECTIFY_URL);
				exceptionDTO.setErrorMessage(ConfigConstants.RECTIFY_URL_MESSAGE);
				exceptionDTO.setStatus("ERROR");
				
				 Response response = Response.status(Response.Status.BAD_REQUEST)
                .entity(exceptionDTO)
                .build();
				requestContext.abortWith(response);
			}
			
		}
		
	}
}