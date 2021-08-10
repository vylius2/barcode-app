package com.barcodegenerator.barcodegenerator.exception;

import lombok.Getter;

@Getter
public class BarcodeDoesNotExist extends RuntimeException{
    private Long id;
    public BarcodeDoesNotExist(Long id){
        this.id = id;
    }
}
