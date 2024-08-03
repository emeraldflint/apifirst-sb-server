package org.emerald.apifirst.apifirstserver.services;

import org.emerald.apifirst.apifirstserver.domain.Customer;
import org.emerald.apifirst.apifirstserver.domain.PaymentMethod;
import org.emerald.apifirst.apifirstserver.repositories.CustomerRepository;
import org.emerald.apifirst.model.AddressDto;
import org.emerald.apifirst.model.CustomerDto;
import org.emerald.apifirst.model.NameDto;
import org.emerald.apifirst.model.PaymentMethodDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    @Test
    void saveNewCustomer() {
        CustomerDto customerDto = createCustomerDTO();

        CustomerDto savedCustomer = customerService.saveNewCustomer(customerDto);

        assertNotNull(savedCustomer);
        assertNotNull(savedCustomer.getId());

        Customer customer = customerRepository.findById(savedCustomer.getId()).orElseThrow();

        assertNotNull(customer.getPaymentMethods());

        PaymentMethod paymentMethod = customer.getPaymentMethods().get(0);

        assertEquals(customerDto.getName().getFirstName(), customer.getName().getFirstName());

    }

    @Test
    void listCustomers() {
    }

    @Test
    void getCustomerById() {
    }

    CustomerDto createCustomerDTO() {
        return CustomerDto.builder()
                .name(NameDto.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .build())
                .billToAddress(AddressDto.builder()
                        .addressLine1("1234 Main Street")
                        .city("San Diego")
                        .state("CA")
                        .zip("92101")
                        .build())
                .shipToAddress(AddressDto.builder()
                        .addressLine1("1234 Main Street")
                        .city("San Diego")
                        .state("CA")
                        .zip("92101")
                        .build())
                .email("joe@example.com")
                .phone("555-555-5555")
                .paymentMethods(Collections.singletonList(PaymentMethodDto.builder()
                        .displayName("My Card")
                        .cardNumber(1234123412)
                        .expiryMonth(12)
                        .expiryYear(2020)
                        .cvv(123).build()))
                .build();
    }
}