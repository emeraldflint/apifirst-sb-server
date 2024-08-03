package org.emerald.apifirst.apifirstserver.repositories;

import org.emerald.apifirst.apifirstserver.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
