package com.mycompany.vcard.exception;

public class ParserException extends RuntimeException {

    public ParserException(String resource) {
        super(String.format("Error for [%s]. Could not parse", resource));
    }
}
