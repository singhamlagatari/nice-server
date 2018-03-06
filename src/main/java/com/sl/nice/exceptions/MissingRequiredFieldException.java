package com.sl.nice.exceptions;

public class MissingRequiredFieldException extends RuntimeException{
    
    private static final long serialVersionUID = 5776681206288518465L;
    
    public MissingRequiredFieldException(String message)
    {
        super(message);
    }
    
}
