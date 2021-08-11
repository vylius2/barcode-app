package com.barcodegenerator.barcodegenerator.service;

import com.barcodegenerator.barcodegenerator.entity.QrBarcode;
import com.barcodegenerator.barcodegenerator.exception.BarcodeDoesNotExist;
import com.barcodegenerator.barcodegenerator.repository.QrBarcodeRepository;
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

    @Value("${barcode.dir}")
    private String path;
    //TODO SUTVARKYT
//    MatrixToImageWriter matrixToImageWriter = new MultiFormatWriter().encode(qrBarCode.getData(), BarcodeFormat.QR_CODE, 500, 500);
    QrBarcodeRepository qrBarcodeRepository;

    public BarcodeService (QrBarcodeRepository qrBarCodeRepository){
        this.qrBarcodeRepository = qrBarCodeRepository;
    }

    /**
     * Takes pre-created QrBarcode object and creates jpg picture of coded barcode,
     * names it with barcode name and append entity id to the end.
     * Width and height of the generated barcode jpg is both set to 500px
     * @param qrBarCode
     */
    public void generateQrBarcode(QrBarcode qrBarCode){

        try {
            qrBarCode.setPath(path + UUID.randomUUID() + ".jpg");
            BitMatrix matrix = new MultiFormatWriter().encode(qrBarCode.getData(), BarcodeFormat.QR_CODE, 500, 500);
            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(qrBarCode.getPath()));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
    public QrBarcode createQrBarcode(QrBarcode qrBarCode){
        generateQrBarcode(qrBarCode);
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
