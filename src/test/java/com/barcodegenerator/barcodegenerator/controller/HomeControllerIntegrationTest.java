package com.barcodegenerator.barcodegenerator.controller;

import com.barcodegenerator.barcodegenerator.dto.response.ErrorResponse;
import com.barcodegenerator.barcodegenerator.entity.QrBarcode;
import com.barcodegenerator.barcodegenerator.repository.QrBarcodeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QrBarcodeRepository qrBarcodeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getQrCodeWhenExist() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/get/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        QrBarcode qrBarcode = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), QrBarcode.class);

        assertEquals(qrBarcode.getId(), 1L);
    }

    @Test
    public void getQrCodeThrowsException() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/get/{id}", 5L))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);

        assertEquals(404, errorResponse.getStatus());
    }
    @Test
    public void getQrCodes() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/get"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        QrBarcode[] qrBarcodes = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), QrBarcode[].class);

        assertEquals(qrBarcodes.length, 2);
    }

}
