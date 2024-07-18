package org.emerald.apifirst.apifirstserver.controllers;

import lombok.RequiredArgsConstructor;
import org.emerald.apifirst.apifirstserver.services.CustomerService;
import org.emerald.apifirst.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.emerald.apifirst.apifirstserver.controllers.CustomerController.BASE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/v1/customers";

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listCustomers() {
        return ResponseEntity.ok(customerService.listCustomers());
    }
}
