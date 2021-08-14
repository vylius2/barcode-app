package com.barcodegenerator.barcodegenerator.service;

import com.barcodegenerator.barcodegenerator.persistence.entity.QrBarcode;
import com.barcodegenerator.barcodegenerator.exception.BarcodeDoesNotExistException;
import com.barcodegenerator.barcodegenerator.persistence.repository.QrBarcodeRepository;
import com.barcodegenerator.barcodegenerator.service.util.QrBarcodeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    private QrBarcodeUtil qrBarcodeUtil;

    @InjectMocks
    private BarcodeService barcodeService;

    @Test
    void testUpdateQrBarcode() {
        QrBarcode barcode = new QrBarcode(1L, "Katinukas", "415684132");
        when(qrBarcodeRepository.save(barcode)).thenReturn(barcode);
        when(qrBarcodeRepository.findById(any(Long.class))).thenReturn(Optional.of(barcode));
        QrBarcode result = barcodeService.updateQrBarcode(barcode);
        verify(qrBarcodeRepository, times(1)).save(barcode);
        assertEquals(result.getName(), barcode.getName());
    }

    @Test
    void testUpdateQrBarcodeThrowsException() {
        QrBarcode barcode = new QrBarcode(1L, "Katinukas", "415684132");
        when(qrBarcodeRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(BarcodeDoesNotExistException.class, () -> barcodeService.updateQrBarcode(barcode));
        verify(qrBarcodeRepository, never()).save(any(QrBarcode.class));
    }



    @Test
    void testCreateQrBarcode() {
        QrBarcode barcode = new QrBarcode(1L, "Katinukas", "415684132");
        doNothing().when(qrBarcodeUtil).generateBarcodeJPG(barcode);
        barcodeService.createQrBarcode(barcode);
        verify(qrBarcodeUtil, times(1)).generateBarcodeJPG(barcode);
        verify(qrBarcodeRepository, times(1)).save(barcode);
    }

    @Test
    void testFindQrBarcodeById() {
        QrBarcode barcode = new QrBarcode(1L, "Katinukas", "415684132");
        when(qrBarcodeRepository.findById(1L)).thenReturn(Optional.of(barcode));
        QrBarcode result = barcodeService.getQrBarcodeById(1L);
        assertEquals(result.getId(), barcode.getId());
        verify(qrBarcodeRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void testFindQrBarcodeByIdThrowsException() {
        when(qrBarcodeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(BarcodeDoesNotExistException.class, () -> barcodeService.getQrBarcodeById(1L));
        verify(qrBarcodeRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void testFindAllQrBarcodes() {
        when(qrBarcodeRepository.findAll()).thenReturn(List.of(new QrBarcode(), new QrBarcode()));
        List<QrBarcode> result = barcodeService.getAllQrBarcodes();
        assertEquals(result.size(), 2);
        verify(qrBarcodeRepository, times(1)).findAll();
    }

    @Test
    void testDeleteQrBarcode() {
        barcodeService.deleteQrBarcode(1L);
        verify(qrBarcodeRepository, times(1)).deleteById(1L);
    }
}