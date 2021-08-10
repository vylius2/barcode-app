package com.barcodegenerator.barcodegenerator.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class UpdateQrBarcodeRequest {

    @NotNull
    @Positive
    private Long id;

    @NotBlank
    @Size(min = 2, max = 25, message = "Invalid name length")
    private String name;

    @NotBlank
    @Size(min = 1, max = 7089, message = "Invalid data length")
    private String data;

    @NotBlank
    private String path;
}
