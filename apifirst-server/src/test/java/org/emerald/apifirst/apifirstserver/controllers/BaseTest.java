package org.emerald.apifirst.apifirstserver.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.emerald.apifirst.apifirstserver.repositories.CustomerRepository;
import org.emerald.apifirst.apifirstserver.repositories.OrderRepository;
import org.emerald.apifirst.apifirstserver.repositories.ProductRepository;
import org.emerald.apifirst.model.CustomerDto;
import org.emerald.apifirst.model.OrderDto;
import org.emerald.apifirst.model.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class BaseTest {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    WebApplicationContext wac;
    @Autowired
    ObjectMapper objectMapper;

    public MockMvc mockMvc;

    CustomerDto testCustomer;
    ProductDto testProduct;
    OrderDto testOrder;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();

        testCustomer = customerRepository.findAll().iterator().next();
        testProduct = productRepository.findAll().iterator().next();
        testOrder = orderRepository.findAll().iterator().next();
    }
}