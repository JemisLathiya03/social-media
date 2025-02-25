package com.socialmedia.Exception;

import com.socialmedia.controller.AuthController;
import com.socialmedia.dto.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LogManager.getLogger(AuthController.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Use a Set to automatically remove duplicates
        Set<String> invalidFields = new HashSet<>();

        // Collect all invalid field names, adding each to the Set
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldError = ((FieldError) error).getField();
            invalidFields.add(fieldError); // Set will ensure uniqueness
        });

        // Create a combined message with all invalid fields
        String combinedMessage = "Please enter valid " + String.join(", ", invalidFields);

        // Create an object with the combined message
        Map<String, String> message = new HashMap<>();
        message.put("message", combinedMessage);

        // Create a response object
        ResponseDto<Object> responseDto = new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "failure", Arrays.asList(message), false);

        // Return the response with BAD_REQUEST status
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<Object> handleUnauthorizedUserErrors(HttpServletRequest req, UnauthorizedUserException ex) {

        log.error("Request: " + req.getRequestURL() + " raised " + ex);

        // Create an object with the combined message
        Map<String, String> message = new HashMap<>();
        message.put("message", ex.getUnauthorizedErrorMessage());

        ResponseDto<Object> responseDto = new ResponseDto<>(HttpStatus.UNAUTHORIZED.value(), "failure", Arrays.asList(message), false);

        return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
    }

}
