package com.academy.ws.exception;

public class NotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public NotFoundException(){super("Element not found!");}

    public NotFoundException(String message){super(message);}
}
