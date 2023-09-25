package com.matera.bootcamp.bank.controller.exception;

import com.matera.bootcamp.bank.exception.InvalidAccountException;
import com.matera.bootcamp.bank.exception.MessageError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CustomControllerAdvice {
    public ResponseEntity<MessageError> invalidAccountException(InvalidAccountException ex) {
        MessageError messageError = new MessageError(ex.getMessage());
        return new ResponseEntity<>(messageError, HttpStatus.BAD_REQUEST);
    }
}
