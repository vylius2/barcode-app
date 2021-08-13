package com.barcodegenerator.barcodegenerator.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.UUID;
public class BarcodeUtil {

    public BitMatrix setupBarcodeTemplate(String dataToEncode, BarcodeFormat barcodeFormat, int barcodeWidth, int barcodeHeight) {
        try {
            return new MultiFormatWriter().encode(dataToEncode, barcodeFormat, barcodeWidth, barcodeHeight);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String generateRandomUniqueFileNameJPG(){
        return UUID.randomUUID() + ".jpg";
    }
}
