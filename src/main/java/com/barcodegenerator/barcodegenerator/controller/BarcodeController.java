package com.barcodegenerator.barcodegenerator.controller;

import com.barcodegenerator.barcodegenerator.dto.request.CreateQrBarcodeRequest;
import com.barcodegenerator.barcodegenerator.dto.request.UpdateQrBarcodeRequest;
import com.barcodegenerator.barcodegenerator.dto.response.CreateQrBarcodeResponse;
import com.barcodegenerator.barcodegenerator.entity.QrBarcode;
import com.barcodegenerator.barcodegenerator.service.BarcodeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BarcodeController {

    BarcodeService barcodeService;

    public BarcodeController(BarcodeService barcodeService){
        this.barcodeService = barcodeService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateQrBarcodeResponse createQrCode(@Valid @RequestBody CreateQrBarcodeRequest createQrBarcodeRequest){
        return new CreateQrBarcodeResponse(barcodeService.createQrBarcode(new QrBarcode(createQrBarcodeRequest)));
    }

    @GetMapping("/get/{id}")
    public QrBarcode getQrBarcode(@PathVariable("id") Long id){
        return barcodeService.findQrBarcodeById(id);
    }

    @GetMapping("/get")
    public List<QrBarcode> getQrBarcodes(){
        return barcodeService.findAllQrBarcodes();
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QrBarcode updateQrBarcode(@PathVariable("id") Long id,
            @Valid @RequestBody UpdateQrBarcodeRequest updateQrBarcodeRequest){
        return barcodeService.updateQrBarcode(new QrBarcode(id, updateQrBarcodeRequest));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQrBarcode(@PathVariable("id") Long id){
        barcodeService.deleteQrBarcode(id);
    }
}
