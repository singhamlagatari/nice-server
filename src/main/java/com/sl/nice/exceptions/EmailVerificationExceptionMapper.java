package com.sl.nice.exceptions;

import com.sl.nice.ui.model.response.ErrorMessage;
import com.sl.nice.ui.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EmailVerificationExceptionMapper implements ExceptionMapper<EmailVerificationException>{

    public Response toResponse(EmailVerificationException exception) {
         ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                ErrorMessages.EMAIL_ADDRESS_NOT_VERIFIED.name(), 
                "http://www.appsdeveloperblog.com");
    
        return Response.status(Response.Status.FORBIDDEN).
                entity(errorMessage).
                build();
    }
    
}
