package com.collective.exception;

public class ButtonNotAvailableException extends Exception{

    public ButtonNotAvailableException(String buttonName){
        super("Button not available " +  buttonName);
    }

    public ButtonNotAvailableException(String buttonName, Exception exception){
        super("Button not available " +  buttonName,  exception);
    }
}
