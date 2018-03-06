package com.sl.nice.exceptions;

import com.sl.nice.ui.model.response.ErrorMessage;
import com.sl.nice.ui.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoRecordFoundExceptionMapper implements ExceptionMapper<NoRecordFoundException>{

    public Response toResponse(NoRecordFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),
                ErrorMessages.NO_RECORD_FOUND.name(), "http://nice.sl.com");
        
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
    
}

