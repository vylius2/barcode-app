package com.barcodegenerator.barcodegenerator.service;

import com.barcodegenerator.barcodegenerator.entity.QrBarcode;
import com.barcodegenerator.barcodegenerator.exception.BarcodeDoesNotExist;
import com.barcodegenerator.barcodegenerator.repository.QrBarcodeRepository;
import com.barcodegenerator.barcodegenerator.util.BarcodeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BarcodeServiceTest {

    @Mock
    private QrBarcodeRepository qrBarcodeRepository;

    @Mock
    private BarcodeUtil barcodeUtil;

    @Spy
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
//        QrBarcode barcode = new QrBarcode(1L, "Katinukas", "415684132");
//        barcodeService.generateQrBarcode(barcode);
    }

    @Test
    void createQrBarcode() {
        QrBarcode barcode = new QrBarcode(1L, "Katinukas", "415684132");
        barcodeService.createQrBarcode(barcode);
        verify(barcodeUtil, times(1)).generateQrBarcode(barcode);
        verify(qrBarcodeRepository, times(1)).save(barcode);
    }

    @Test
    void findQrBarcodeById() {
        QrBarcode barcode = new QrBarcode(1L, "Katinukas", "415684132");
        when(qrBarcodeRepository.findQrBarcodeById(1L)).thenReturn(Optional.of(barcode));
        QrBarcode result = barcodeService.findQrBarcodeById(1L);
        assertEquals(result.getId(), barcode.getId());
        verify(qrBarcodeRepository, times(1)).findQrBarcodeById(any(Long.class));
    }

    @Test
    void findQrBarcodeByIdThrowsException() {
        when(qrBarcodeRepository.findQrBarcodeById(1L)).thenReturn(Optional.empty());
        assertThrows(BarcodeDoesNotExist.class, () -> barcodeService.findQrBarcodeById(1L));
        verify(qrBarcodeRepository, times(1)).findQrBarcodeById(any(Long.class));
    }

    @Test
    void findAllQrBarcodes() {
        when(qrBarcodeRepository.findAll()).thenReturn(List.of(new QrBarcode(), new QrBarcode()));
        List<QrBarcode> result = barcodeService.findAllQrBarcodes();
        assertEquals(result.size(), 2);
        verify(qrBarcodeRepository, times(1)).findAll();
    }

    @Test
    void deleteQrBarcode() {
        barcodeService.deleteQrBarcode(1L);
        verify(qrBarcodeRepository, times(1)).deleteById(1L);
    }
}