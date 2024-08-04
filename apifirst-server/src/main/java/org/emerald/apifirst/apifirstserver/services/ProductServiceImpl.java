package org.emerald.apifirst.apifirstserver.services;

import lombok.RequiredArgsConstructor;
import org.emerald.apifirst.apifirstserver.domain.Product;
import org.emerald.apifirst.apifirstserver.mappers.ProductMapper;
import org.emerald.apifirst.apifirstserver.repositories.ProductRepository;
import org.emerald.apifirst.model.ProductCreateDto;
import org.emerald.apifirst.model.ProductDto;
import org.emerald.apifirst.model.ProductPatchDto;
import org.emerald.apifirst.model.ProductUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> listProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(productMapper::productToDto)
                .toList();
    }

    @Override
    public ProductDto getProductById(UUID productId) {
        return productMapper.productToDto(productRepository.findById(productId).orElseThrow());
    }

    @Override
    public ProductDto saveNewProduct(ProductCreateDto product) {
        return productMapper.productToDto(productRepository.save(productMapper.dtoToProduct(product)));
    }

    @Override
    public ProductDto updateProduct(UUID productId, ProductUpdateDto productUpdateDto) {
        var product = productRepository.findById(productId).orElseThrow();
        productMapper.updateProduct(productUpdateDto, product);

        return productMapper.productToDto(productRepository.save(product));
    }

    @Override
    public ProductDto patchProduct(UUID productId, ProductPatchDto product) {
        Product existingProduct = productRepository.findById(productId).orElseThrow();
        productMapper.patchProduct(product, existingProduct);
        return productMapper.productToDto(productRepository.save(existingProduct));
    }

    @Override
    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }
}