package com.eluanps.crudjsfprimefaces.util;

public class Errors extends Exception{
    
    public Errors(String msg){
        
    }
    
    public Errors(String msg, Throwable cause){
        super(msg, cause);
    }
    
}