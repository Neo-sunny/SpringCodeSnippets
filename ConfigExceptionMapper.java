package com.zyme.config.model.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.zyme.config.persistence.model.entity.ConfigExceptionDTO;

public class ConfigExceptionMapper extends Exception implements ExceptionMapper<ConfigServiceException> {

	private static final long serialVersionUID = -1040227914656695763L;

	@Override
	public Response toResponse(ConfigServiceException ex) {
		ConfigExceptionDTO exceptionDTO = new ConfigExceptionDTO();
		exceptionDTO.setErrorMessage(ex.getMessage());
		exceptionDTO.setErrorCode(ex.getErrorCode());
		exceptionDTO.setStatus("Error");
		 Response response = Response.status(Response.Status.BAD_REQUEST)
	                .entity(exceptionDTO)
	                .build();
		return response;
	}

}
