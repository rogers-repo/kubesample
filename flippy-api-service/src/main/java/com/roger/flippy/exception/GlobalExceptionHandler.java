package com.stackroute.flippy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {CustomerNotFoundException.class,OrderNotFoundException.class})
    public ResponseEntity<ErrorRespose> handlesCustomerAndOrderNotFound(final RuntimeException exception,final HttpServletRequest request) {
     return new ResponseEntity<ErrorRespose>(new ErrorRespose(HttpStatus.NOT_FOUND,exception.getCause().getMessage(), LocalDateTime.now()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderExistsException.class)
    public ResponseEntity<ErrorRespose> handlesOrderExists(final OrderExistsException exception,final HttpServletRequest request) {
        return new ResponseEntity<ErrorRespose>(new ErrorRespose(HttpStatus.CONFLICT,exception.getCause().getMessage(), LocalDateTime.now()),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRespose> handlesException(final Exception exception,final HttpServletRequest request) {
        return new ResponseEntity<ErrorRespose>(new ErrorRespose(HttpStatus.INTERNAL_SERVER_ERROR,exception.getCause().getMessage(), LocalDateTime.now()),HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorRespose> handleRuntimeException(final RuntimeException exception,final HttpServletRequest request) {
        return new ResponseEntity<ErrorRespose>(new ErrorRespose(HttpStatus.INTERNAL_SERVER_ERROR,exception.getCause().getMessage(), LocalDateTime.now()),HttpStatus.INTERNAL_SERVER_ERROR);
     }



}
