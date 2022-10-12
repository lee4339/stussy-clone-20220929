package com.stussy.stussyclone20220929.exception;


public class CustomInternalServerErrorException extends RuntimeException{

    public CustomInternalServerErrorException(String message) {
        super(message);
    }
}
