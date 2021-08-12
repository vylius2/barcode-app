package com.barcodegenerator.barcodegenerator;

import com.barcodegenerator.barcodegenerator.service.BarcodeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BarcodeGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarcodeGeneratorApplication.class, args);
    }

}
