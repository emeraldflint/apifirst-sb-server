package org.emerald.apifirst.apifirstserver.controllers;

import org.emerald.apifirst.model.OrderCreateDto;
import org.emerald.apifirst.model.OrderLineCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class OrderControllerTest extends BaseTest {

    @Test
    @Transactional
    void testCreateOrder() throws Exception {
        OrderCreateDto orderCreate = OrderCreateDto.builder()
                .customerId(testCustomer.getId())
                .selectPaymentMethodId(testCustomer.getPaymentMethods().get(0).getId())
                .orderLines(Collections.singletonList(OrderLineCreateDto.builder()
                        .productId(testProduct.getId())
                        .orderQuantity(1)
                        .build()))
                .build();

        System.out.println(objectMapper.writeValueAsString(orderCreate));

        mockMvc.perform(post(OrderController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderCreate)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void listOrders() throws Exception {
        mockMvc.perform(get(OrderController.BASE_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    void getOrderById() throws Exception {
        mockMvc.perform(get(OrderController.BASE_URL + "/{orderId}", testOrder.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(testOrder.getId().toString())));
    }

    @Test
    @Transactional
    void testUpdateOrder() throws Exception {

        var order = orderRepository.findAll().get(0);

        order.getOrderLines().get(0).setOrderQuantity(222);

        var orderUpdate = orderMapper.orderToUpdateDto(order);

        mockMvc.perform(put(OrderController.BASE_URL + "/{orderId}", testOrder.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderUpdate))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(testOrder.getId().toString())))
                .andExpect(jsonPath("$.orderLines[0].orderQuantity", equalTo(222)));
    }
}