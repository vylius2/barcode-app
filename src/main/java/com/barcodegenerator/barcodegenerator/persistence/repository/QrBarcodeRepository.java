package com.barcodegenerator.barcodegenerator.persistence.repository;

import com.barcodegenerator.barcodegenerator.persistence.entity.QrBarcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrBarcodeRepository extends JpaRepository<QrBarcode, Long> {
}
