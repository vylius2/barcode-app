package com.barcodegenerator.barcodegenerator.api.controller;

import com.barcodegenerator.barcodegenerator.api.dto.request.CreateQrBarcodeRequest;
import com.barcodegenerator.barcodegenerator.api.dto.request.UpdateQrBarcodeRequest;
import com.barcodegenerator.barcodegenerator.api.dto.response.ErrorResponse;
import com.barcodegenerator.barcodegenerator.persistence.entity.QrBarcode;
import com.barcodegenerator.barcodegenerator.persistence.repository.QrBarcodeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    public void testGetQrCodeWhenExist() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/get/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        QrBarcode qrBarcode = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), QrBarcode.class);

        assertEquals(qrBarcode.getId(), 1L);
    }

    @Test
    public void testGetQrCodeThrowsException() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/get/{id}", 5L))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);

        assertEquals(404, errorResponse.getStatus());
    }
    @Test
    public void testGetQrCodes() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/get"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        QrBarcode[] qrBarcodes = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), QrBarcode[].class);

        assertEquals(qrBarcodes.length, 2);
    }
    @Test
    public void testCreateQrCode() throws Exception{
        CreateQrBarcodeRequest createQrBarcodeRequest = new CreateQrBarcodeRequest("Pasta", "4865132");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/create")
                .content(objectMapper.writeValueAsString(createQrBarcodeRequest))
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        QrBarcode qrBarcode = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), QrBarcode.class);

        assertEquals(qrBarcode.getId(), 3L);
        assertEquals(qrBarcode.getData(), qrBarcodeRepository.findById(3L).get().getData());
    }

    @Test
    public void testDeleteQrCode()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/{id}", 3L))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();

        assertFalse(qrBarcodeRepository.existsById(3L));
    }

    @Test
    public void testCreateQrCodeInvalidBody()throws Exception{
        CreateQrBarcodeRequest createQrBarcodeRequest = new CreateQrBarcodeRequest("", "4865132");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/create")
                .content(objectMapper.writeValueAsString(createQrBarcodeRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);

        assertEquals(errorResponse.getStatus(), 400);
    }

    @Test
    public void testUpdateQrCodeInvalidBody()throws Exception{
        UpdateQrBarcodeRequest updateQrBarcodeRequest = new UpdateQrBarcodeRequest("", "486512", "qqq");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/update/{id}", 1L)
                .content(objectMapper.writeValueAsString(updateQrBarcodeRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);

        assertEquals(400, errorResponse.getStatus());
    }
    @Test
    public void testUpdateQrCode()throws Exception{
        UpdateQrBarcodeRequest updateQrBarcodeRequest = new UpdateQrBarcodeRequest("qwerf", "48651311", "qqq");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/update/{id}", 2L)
                .content(objectMapper.writeValueAsString(updateQrBarcodeRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        QrBarcode qrBarcode = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), QrBarcode.class);

        assertEquals(qrBarcode, qrBarcodeRepository.findById(2L).get());
    }

}
