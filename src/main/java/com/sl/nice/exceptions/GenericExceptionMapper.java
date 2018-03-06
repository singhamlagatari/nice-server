package com.sl.nice.exceptions;

import com.sl.nice.ui.model.response.ErrorMessage;
import com.sl.nice.ui.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    public Response toResponse(Throwable exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),
                ErrorMessages.INTERNAL_SERVER_ERROR.name(), "http://nice.sl.com");

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                entity(errorMessage).
                build();
    }

}
