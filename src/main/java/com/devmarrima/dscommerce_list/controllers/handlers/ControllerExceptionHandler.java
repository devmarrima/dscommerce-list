package com.devmarrima.dscommerce_list.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devmarrima.dscommerce_list.dto.CustomError;
import com.devmarrima.dscommerce_list.services.exceptions.DataBaseException;
import com.devmarrima.dscommerce_list.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> ResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataBaseException.class)
            public ResponseEntity<CustomError> database(DataBaseException e, HttpServletRequest request) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            CustomError err = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
            return ResponseEntity.status(status).body(err);
            }
        }