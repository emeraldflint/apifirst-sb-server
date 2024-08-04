package org.emerald.apifirst.apifirstserver.services;

import org.emerald.apifirst.model.CustomerDto;
import org.emerald.apifirst.model.CustomerPatchDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<CustomerDto> listCustomers();

    CustomerDto getCustomerById(UUID customerId);

    CustomerDto saveNewCustomer(CustomerDto customer);

    CustomerDto updateCustomer(UUID customerId, CustomerDto customer);

    CustomerDto patchCustomer(UUID customerId, CustomerPatchDto customer);

    void deleteCustomer(UUID customerId);
}
