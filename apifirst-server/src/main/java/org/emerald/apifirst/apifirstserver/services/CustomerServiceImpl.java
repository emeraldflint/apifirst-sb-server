package org.emerald.apifirst.apifirstserver.services;

import lombok.RequiredArgsConstructor;
import org.emerald.apifirst.apifirstserver.domain.Customer;
import org.emerald.apifirst.apifirstserver.mappers.CustomerMapper;
import org.emerald.apifirst.apifirstserver.repositories.CustomerRepository;
import org.emerald.apifirst.model.CustomerDto;
import org.emerald.apifirst.model.CustomerPatchDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> listCustomers() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .map(customerMapper::customerToDto)
                .toList();
    }

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return customerMapper.customerToDto(customerRepository.findById(customerId).orElseThrow());
    }

    @Transactional
    @Override
    public CustomerDto saveNewCustomer(CustomerDto customer) {
        Customer savedCustomer = customerRepository.save(customerMapper.dtoToCustomer(customer));
        customerRepository.flush();
        return customerMapper.customerToDto(savedCustomer);
    }

    @Transactional
    @Override
    public CustomerDto updateCustomer(UUID customerId, CustomerDto customer) {
        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow();
        customerMapper.updateCustomer(customer, existingCustomer);

        return customerMapper.customerToDto(customerRepository.save(existingCustomer));
    }

    @Transactional
    @Override
    public CustomerDto patchCustomer(UUID customerId, CustomerPatchDto customer) {
        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow();

        customerMapper.patchCustomer(customer, existingCustomer);

        return customerMapper.customerToDto(customerRepository.saveAndFlush(existingCustomer));
    }

    @Transactional
    @Override
    public void deleteCustomer(UUID customerId) {
        customerRepository.deleteById(customerId);
    }
}
