package org.emerald.apifirst.apifirstserver.controllers;

import lombok.RequiredArgsConstructor;
import org.emerald.apifirst.apifirstserver.services.CustomerService;
import org.emerald.apifirst.model.CustomerDto;
import org.emerald.apifirst.model.CustomerPatchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.emerald.apifirst.apifirstserver.controllers.CustomerController.BASE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/v1/customers";

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> listCustomers() {
        return ResponseEntity.ok(customerService.listCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") UUID customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @PostMapping
    ResponseEntity<Void> saveNewCustomer(@RequestBody CustomerDto customer){
        var savedCustomer = customerService.saveNewCustomer(customer);
        return ResponseEntity.created(URI.create(BASE_URL + "/" + savedCustomer.getId())).build();
    }

    @PutMapping("/{customerId}")
    ResponseEntity<CustomerDto> updateCustomer(@PathVariable("customerId") UUID customerId,
                                               @RequestBody CustomerDto customer){
        CustomerDto savedCustomer = customerService.updateCustomer(customerId, customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @PatchMapping("/{customerId}")
    ResponseEntity<CustomerDto> patchCustomer(@PathVariable("customerId") UUID customerId,
                                              @RequestBody CustomerPatchDto customer){
        CustomerDto savedCustomer = customerService.patchCustomer(customerId, customer);
        return ResponseEntity.ok(savedCustomer);
    }
}
