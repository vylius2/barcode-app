package com.barcodegenerator.barcodegenerator.persistence.entity;

import com.barcodegenerator.barcodegenerator.api.dto.request.CreateQrBarcodeRequest;
import com.barcodegenerator.barcodegenerator.api.dto.request.UpdateQrBarcodeRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "qr_code")
@NoArgsConstructor
public class QrBarcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "uniq_code")
    private String data;

    private String path;

    public QrBarcode(CreateQrBarcodeRequest createQrBarcodeRequest){
        this.data = createQrBarcodeRequest.getData();
        this.name = createQrBarcodeRequest.getName();
    }

    public QrBarcode(Long id, String name, String data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public QrBarcode(Long id, UpdateQrBarcodeRequest updateQrBarcodeRequest){
        this.id = id;
        this.data = updateQrBarcodeRequest.getData();
        this.name = updateQrBarcodeRequest.getName();
        this.path = updateQrBarcodeRequest.getPath();
    }
}
