package com.nghiem.messenger.exception;

import com.nghiem.messenger.model.ErrorMessage;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), exception.getResponse().getStatusInfo().getStatusCode(), null);
        
        return Response.status(errorMessage.getErrorCode())
                .entity(errorMessage)
                .build();
    }
}
