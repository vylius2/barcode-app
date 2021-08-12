package com.barcodegenerator.barcodegenerator.exception;

import com.barcodegenerator.barcodegenerator.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BarcodeDoesNotExistException.class)
    public ResponseEntity<ErrorResponse> handle(BarcodeDoesNotExistException e){
        String message = String.format("Barcode with id = %d was not found", e.getId());
        ErrorResponse errorResponse = new ErrorResponse(message, 404);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e) {
        String message = "Invalid request body provided";
        ErrorResponse errorResponse = new ErrorResponse(message, 400);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
