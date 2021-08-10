package com.barcodegenerator.barcodegenerator.dto.response;

import com.barcodegenerator.barcodegenerator.entity.QrBarcode;
import lombok.Data;

@Data
public class CreateQrBarcodeResponse {

    private Long id;

    private String name;

    private String data;

    private String path;

    public CreateQrBarcodeResponse(QrBarcode qrBarCode){
        this.id = qrBarCode.getId();
        this.name = qrBarCode.getName();
        this.data = qrBarCode.getData();
        this.path = qrBarCode.getPath();
    }
}
