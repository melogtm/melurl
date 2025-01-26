package com.melogtm.tinyurl.handlers;

import com.melogtm.tinyurl.exceptions.ErrrorDetails;
import com.melogtm.tinyurl.exceptions.UrlAlreadyExistsException;
import com.melogtm.tinyurl.exceptions.UrlNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<Object> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        ErrrorDetails errorDetails = new ErrrorDetails("Not Supported Request Method", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleInvalidation(MethodArgumentNotValidException e, WebRequest web) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> errors.put("Violation: ",
                error.getDefaultMessage()));

        ErrrorDetails errorDetails = new ErrrorDetails("Invalid Request", errors.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UrlAlreadyExistsException.class)
    public final ResponseEntity<Object> handleUrlAlreadyExistsException(UrlAlreadyExistsException e) {
        ErrrorDetails errorDetails = new ErrrorDetails("Unfulfilled Request", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UrlNotFoundException.class)
    public final ResponseEntity<Object> handleUrlNotFoundException(UrlNotFoundException e) {
        ErrrorDetails errorDetails = new ErrrorDetails("Url Not Found", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
