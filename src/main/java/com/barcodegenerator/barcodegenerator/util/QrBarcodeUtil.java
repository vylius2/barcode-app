package com.barcodegenerator.barcodegenerator.util;

import com.barcodegenerator.barcodegenerator.entity.QrBarcode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;

@Component
public class QrBarcodeUtil extends BarcodeUtil{
    @Value("${qr.barcode.dir}")
    private String path;

    @Value("${qr.barcode.width}")
    private int width;

    @Value("${qr.barcode.height}")
    private int height;

    public void generateBarcodeJPG(QrBarcode qrBarcode) {
        assignFileNameJPG(qrBarcode);
        try {
            MatrixToImageWriter.writeToPath(
                    setupBarcodeTemplate(qrBarcode.getData(), BarcodeFormat.QR_CODE, width, height),
                    "jpg",
                    Paths.get(qrBarcode.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void assignFileNameJPG(QrBarcode qrBarcode){
        qrBarcode.setPath(path + generateRandomUniqueFileNameJPG());
    }


}
