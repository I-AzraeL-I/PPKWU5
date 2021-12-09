package com.mycompany.vcard.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ParserException.class)
    public ResponseEntity<String> handleParserException(ParserException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
