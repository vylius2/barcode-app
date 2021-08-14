package com.barcodegenerator.barcodegenerator.service.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BaseBarcodeTest {


    @Spy
    private BaseBarcode baseBarcode;


    @Test
    public void testGenerateRandomUniqueFileNameJPG(){
        String testSubject = baseBarcode.generateRandomUniqueFileNameJPG();
        assertTrue(testSubject.endsWith(".jpg"));
        assertFalse(testSubject.substring(0, testSubject.length()-4).isBlank());
    }

    @Test
    public void testSetupBarcodeTemplate() throws WriterException {
        BitMatrix testSubject = baseBarcode.setupBarcodeTemplate("placeholder", BarcodeFormat.QR_CODE, 100, 100);
        BitMatrix testSubject1 = new MultiFormatWriter().encode("placeholder", BarcodeFormat.QR_CODE, 100, 100);
        assertEquals(testSubject1, testSubject);
    }
}
