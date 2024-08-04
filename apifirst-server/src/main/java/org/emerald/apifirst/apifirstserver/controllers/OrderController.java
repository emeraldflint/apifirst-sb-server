package org.emerald.apifirst.apifirstserver.controllers;

import lombok.RequiredArgsConstructor;
import org.emerald.apifirst.apifirstserver.services.OrderService;
import org.emerald.apifirst.model.OrderCreateDto;
import org.emerald.apifirst.model.OrderDto;
import org.emerald.apifirst.model.OrderUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.emerald.apifirst.apifirstserver.controllers.OrderController.BASE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL)
public class OrderController {

    public static final String BASE_URL = "/v1/orders";

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> saveNewOrder(@RequestBody OrderCreateDto orderCreate){
        OrderDto savedOrder = orderService.saveNewOrder(orderCreate);

        UriComponents uriComponents = UriComponentsBuilder.fromPath(BASE_URL + "/{orderId}")
                .buildAndExpand(savedOrder.getId());

        return ResponseEntity.created(URI.create(uriComponents.getPath())).build();
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> listOrders(){
        return ResponseEntity.ok(orderService.listOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getProductById(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable UUID orderId, @RequestBody OrderUpdateDto order) {
        var updatedProduct = orderService.updateOrder(orderId, order);
        return ResponseEntity.ok(updatedProduct);
    }
}