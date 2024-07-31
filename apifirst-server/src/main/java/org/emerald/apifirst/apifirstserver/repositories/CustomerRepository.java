package org.emerald.apifirst.apifirstserver.repositories;

import org.emerald.apifirst.model.CustomerDto;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<CustomerDto, UUID> {
}
