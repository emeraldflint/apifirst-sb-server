package org.emerald.apifirst.apifirstserver.services;

import org.emerald.apifirst.model.ProductCreateDto;
import org.emerald.apifirst.model.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDto> listProducts();

    ProductDto getProductById(UUID productId);

    ProductDto saveNewProduct(ProductCreateDto product);
}