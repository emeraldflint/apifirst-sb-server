package org.emerald.apifirst.apifirstserver.services;

import lombok.RequiredArgsConstructor;
import org.emerald.apifirst.apifirstserver.repositories.ProductRepository;
import org.emerald.apifirst.model.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> listProducts() {
       /* return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .toList();*/
        return null;
    }

    @Override
    public ProductDto getProductById(UUID productId) {
        //return productRepository.findById(productId).orElseThrow();
        return null;
    }

    @Override
    public ProductDto saveNewProduct(ProductDto product) {
        //return productRepository.save(product);
        return null;
    }
}