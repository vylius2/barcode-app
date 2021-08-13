package com.barcodegenerator.barcodegenerator.controller;

import com.barcodegenerator.barcodegenerator.dto.request.CreateQrBarcodeRequest;
import com.barcodegenerator.barcodegenerator.dto.request.UpdateQrBarcodeRequest;
import com.barcodegenerator.barcodegenerator.dto.response.CreateQrBarcodeResponse;
import com.barcodegenerator.barcodegenerator.entity.QrBarcode;
import com.barcodegenerator.barcodegenerator.service.BarcodeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BarcodeController {

    BarcodeService barcodeService;

    public BarcodeController(BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    @ApiOperation(value = "Create Qr Barcode", tags = "createQrBarcode", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully create car record"),
            @ApiResponse(code = 400, message = "Validation failed"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateQrBarcodeResponse createQrCode(@Valid @RequestBody CreateQrBarcodeRequest createQrBarcodeRequest) {
        return new CreateQrBarcodeResponse(barcodeService.createQrBarcode(new QrBarcode(createQrBarcodeRequest)));
    }

    @ApiOperation(value = "Get Qr Barcode", tags = "getQrBarcode", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get car record by id"),
            @ApiResponse(code = 404, message = "Car not found error"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden access to endpoint"),
            @ApiResponse(code = 405, message = "Method not allowed")

    })
    @GetMapping("/get/{id}")
    public QrBarcode getQrBarcode(@PathVariable("id") Long id) {
        return barcodeService.findQrBarcodeById(id);
    }

    @ApiOperation(value = "Get Qr Barcodes", tags = "getQrBarcodes", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get car records"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden access to endpoint")
    })
    @GetMapping("/get")
    public List<QrBarcode> getQrBarcodes() {
        return barcodeService.findAllQrBarcodes();
    }

    @ApiOperation(value = "Update Qr Barcode", tags = "updateQrBarcode", httpMethod = "PUT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Barcode succesfully updated"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden access to endpoint"),
            @ApiResponse(code = 400, message = "Validation failed")
    })
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QrBarcode updateQrBarcode(@PathVariable("id") Long id,
                                     @Valid @RequestBody UpdateQrBarcodeRequest updateQrBarcodeRequest) {
        return barcodeService.updateQrBarcode(new QrBarcode(id, updateQrBarcodeRequest));
    }

    @ApiOperation(value = "Delete Qr Barcode", tags = "deleteQrBarcode", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden access to endpoint"),
            @ApiResponse(code = 404, message = "Barcode does not exist")
    })
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQrBarcode(@PathVariable("id") Long id) {
        barcodeService.deleteQrBarcode(id);
    }
}
