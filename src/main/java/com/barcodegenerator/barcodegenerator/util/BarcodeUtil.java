package com.barcodegenerator.barcodegenerator.util;

import com.barcodegenerator.barcodegenerator.entity.QrBarcode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;
@Component
public class BarcodeUtil {
    @Value("${barcode.dir}")
    private String path;
    //TODO SUTVARKYT
    //    MatrixToImageWriter matrixToImageWriter = new MultiFormatWriter().encode(qrBarCode.getData(), BarcodeFormat.QR_CODE, 500, 500);
    public void generateQrBarcode(QrBarcode qrBarCode){

        try {
            qrBarCode.setPath(path + UUID.randomUUID() + ".jpg");
            BitMatrix matrix = new MultiFormatWriter().encode(qrBarCode.getData(), BarcodeFormat.QR_CODE, 500, 500);
            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(qrBarCode.getPath()));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}
