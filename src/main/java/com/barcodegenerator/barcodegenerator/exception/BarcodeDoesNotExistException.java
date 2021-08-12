package com.barcodegenerator.barcodegenerator.exception;

import lombok.Getter;

@Getter
public class BarcodeDoesNotExistException extends RuntimeException{
    private Long id;
    public BarcodeDoesNotExistException(Long id){
        this.id = id;
    }
}
