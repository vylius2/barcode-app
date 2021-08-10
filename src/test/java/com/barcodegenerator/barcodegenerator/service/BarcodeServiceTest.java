package com.barcodegenerator.barcodegenerator.service;

import com.barcodegenerator.barcodegenerator.entity.QrBarcode;
import com.barcodegenerator.barcodegenerator.exception.BarcodeDoesNotExist;
import com.barcodegenerator.barcodegenerator.repository.QrBarcodeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.lang.Nullable;

import java.util.Optional;

import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BarcodeServiceTest {

    @Mock
    private QrBarcodeRepository qrBarcodeRepository;

    @InjectMocks
    private BarcodeService barcodeService;

    @Test
    void updateQrBarcode() {
        QrBarcode barcode = new QrBarcode(1L, "Katinukas", "415684132");
        when(qrBarcodeRepository.save(barcode)).thenReturn(barcode);
        when(qrBarcodeRepository.findQrBarcodeById(any(Long.class))).thenReturn(Optional.of(barcode));
        QrBarcode result = barcodeService.updateQrBarcode(barcode);
        verify(qrBarcodeRepository, times(1)).save(barcode);
        assertEquals(result.getName(), barcode.getName());
    }

    @Test
    void updateQrBarcodeThrowsException() {
        QrBarcode barcode = new QrBarcode(1L, "Katinukas", "415684132");
        when(qrBarcodeRepository.findQrBarcodeById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(BarcodeDoesNotExist.class, () -> barcodeService.updateQrBarcode(barcode));
        verify(qrBarcodeRepository, never()).save(any(QrBarcode.class));
    }

    @Test
    void generateQrBarcode() {
        QrBarcode barcode = new QrBarcode(1L, "Katinukas", "415684132");
        barcodeService.generateQrBarcode(barcode);
    }

    @Test
    void createQrBarcode() {
    }

    @Test
    void findQrBarcodeById() {
    }

    @Test
    void findAllQrBarcodes() {
    }

    @Test
    void deleteQrBarcode() {
    }
}