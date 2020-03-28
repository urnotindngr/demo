package com.github.jnglman.demo.first;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jnglman.demo.first.model.Document;
import com.github.jnglman.demo.first.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DocumentController.class)
class DocumentControllerTest {

    private static final String URL_TEMPLATE = "/products";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testNullBodyIsBadRequest() throws Exception {
        mockMvc.perform(post(URL_TEMPLATE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testIncorrectFieldLengthIsBadRequest() throws Exception {
        Product product = new Product("wrong", "1234");
        Document document = new Document("1234", "1234", Collections.singletonList(product));

        mockMvc.perform(post(URL_TEMPLATE)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(document)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(3)))
                .andDo(print());
    }

    @Test
    void expectOnlyDigitsInCodes() throws Exception {
        Product product = new Product("wrong", "123456789AQUA");
        Document document = new Document("12345678m", "12345678n", Collections.singletonList(product));

        mockMvc.perform(post(URL_TEMPLATE)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(document)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(3)))
                .andDo(print());
    }

    @Test
    void testProductsShouldNotBeEmtpy() throws Exception{
        Document document = new Document("123456789", "123456789", Collections.emptyList());

        mockMvc.perform(post(URL_TEMPLATE)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(document)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void testCorrectBodyIsOk() throws Exception {
        Product product = new Product("so good", "1234567890111");
        Document document = new Document("123456789", "123456789", Collections.singletonList(product));

        mockMvc.perform(post(URL_TEMPLATE)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(document)))
                .andExpect(status().isOk());
    }
}