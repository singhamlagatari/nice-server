package com.sl.nice.exceptions;

import com.sl.nice.ui.model.response.ErrorMessage;
import com.sl.nice.ui.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CouldNotCreateRecordExceptionMapper implements ExceptionMapper<CouldNotCreateRecordException>{

    public Response toResponse(CouldNotCreateRecordException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),
                ErrorMessages.RECORD_ALREADY_EXISTS.name(), "http://nice.sl.com");
        
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
}
