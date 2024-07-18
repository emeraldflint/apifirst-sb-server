package org.emerald.apifirst.apifirstserver.services;

import lombok.RequiredArgsConstructor;
import org.emerald.apifirst.apifirstserver.repositories.CustomerRepository;
import org.emerald.apifirst.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> listCustomers() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false).toList();
    }
}
