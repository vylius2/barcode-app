package com.barcodegenerator.barcodegenerator.service;

import com.barcodegenerator.barcodegenerator.entity.QrBarcode;
import com.barcodegenerator.barcodegenerator.exception.BarcodeDoesNotExistException;
import com.barcodegenerator.barcodegenerator.repository.QrBarcodeRepository;
import com.barcodegenerator.barcodegenerator.util.QrBarcodeUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarcodeService {

    private final QrBarcodeRepository qrBarcodeRepository;

    private final QrBarcodeUtil qrBarcodeUtil;

    public BarcodeService (QrBarcodeRepository qrBarCodeRepository, QrBarcodeUtil qrBarcodeUtil){
        this.qrBarcodeRepository = qrBarCodeRepository;
        this.qrBarcodeUtil = qrBarcodeUtil;
    }

    public QrBarcode createQrBarcode(QrBarcode qrBarCode){
        qrBarcodeUtil.generateBarcodeJPG(qrBarCode);
        return qrBarcodeRepository.save(qrBarCode);
    }

    public QrBarcode findQrBarcodeById(Long id){
        return qrBarcodeRepository.findById(id).orElseThrow(() -> new BarcodeDoesNotExistException(id));
    }

    public List<QrBarcode> findAllQrBarcodes(){
        return qrBarcodeRepository.findAll();
    }

    public QrBarcode updateQrBarcode(QrBarcode qrBarcode){
        if (qrBarcodeRepository.findById(qrBarcode.getId()).isEmpty()){
            throw new BarcodeDoesNotExistException(qrBarcode.getId());
        }
        return qrBarcodeRepository.save(qrBarcode);

    }

    public void deleteQrBarcode(Long id){

        qrBarcodeRepository.deleteById(id);
    }

}
