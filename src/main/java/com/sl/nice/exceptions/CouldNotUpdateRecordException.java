package com.sl.nice.exceptions;

public class CouldNotUpdateRecordException extends RuntimeException {

    private static final long serialVersionUID = 6262079154830496023L;
    
    public CouldNotUpdateRecordException(String message)
    {
        super(message);
    }
    
}
