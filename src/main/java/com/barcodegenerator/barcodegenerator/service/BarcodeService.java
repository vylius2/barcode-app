package com.barcodegenerator.barcodegenerator.service;

import com.barcodegenerator.barcodegenerator.entity.QrBarcode;
import com.barcodegenerator.barcodegenerator.exception.BarcodeDoesNotExist;
import com.barcodegenerator.barcodegenerator.repository.QrBarcodeRepository;
import com.barcodegenerator.barcodegenerator.util.BarcodeUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class BarcodeService {

    QrBarcodeRepository qrBarcodeRepository;

    BarcodeUtil barcodeUtil;

    public BarcodeService (QrBarcodeRepository qrBarCodeRepository, BarcodeUtil barcodeUtil){
        this.qrBarcodeRepository = qrBarCodeRepository;
        this.barcodeUtil = barcodeUtil;
    }

    public QrBarcode createQrBarcode(QrBarcode qrBarCode){
        barcodeUtil.generateQrBarcode(qrBarCode);
        return qrBarcodeRepository.save(qrBarCode);
    }

    public QrBarcode findQrBarcodeById(Long id){
        return qrBarcodeRepository.findQrBarcodeById(id).orElseThrow(() -> new BarcodeDoesNotExist(id));
    }

    public List<QrBarcode> findAllQrBarcodes(){
        return qrBarcodeRepository.findAll();
    }

    public QrBarcode updateQrBarcode(QrBarcode qrBarcode){
        if (qrBarcodeRepository.findQrBarcodeById(qrBarcode.getId()).isEmpty()){
            throw new BarcodeDoesNotExist(qrBarcode.getId());
        }
        return qrBarcodeRepository.save(qrBarcode);

    }

    public void deleteQrBarcode(Long id){

        qrBarcodeRepository.deleteById(id);
    }

}
