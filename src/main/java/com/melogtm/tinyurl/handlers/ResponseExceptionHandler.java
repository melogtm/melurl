package com.melogtm.tinyurl.handlers;

import com.melogtm.tinyurl.exceptions.ErrorTemplate;
import com.melogtm.tinyurl.exceptions.UrlAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleInvalidation(MethodArgumentNotValidException e, WebRequest web) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(),
                error.getDefaultMessage()));

        ErrorTemplate errorDetails = new ErrorTemplate("Invalid Request", errors.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UrlAlreadyExistsException.class)
    public final ResponseEntity<Object> handleUrlAlreadyExistsException(UrlAlreadyExistsException e) {
        ErrorTemplate errorDetails = new ErrorTemplate("Unfulfilled Request", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
