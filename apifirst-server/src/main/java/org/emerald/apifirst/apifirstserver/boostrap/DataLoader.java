package org.emerald.apifirst.apifirstserver.boostrap;

import lombok.RequiredArgsConstructor;
import org.emerald.apifirst.apifirstserver.repositories.CustomerRepository;
import org.emerald.apifirst.apifirstserver.repositories.ProductRepository;
import org.emerald.apifirst.model.Address;
import org.emerald.apifirst.model.Category;
import org.emerald.apifirst.model.Customer;
import org.emerald.apifirst.model.Dimensions;
import org.emerald.apifirst.model.Image;
import org.emerald.apifirst.model.Name;
import org.emerald.apifirst.model.PaymentMethod;
import org.emerald.apifirst.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        Address address1 = Address.builder()
                .addressLine1("1234 W Some Street")
                .city("Some City")
                .state("FL")
                .zip("33701")
                .build();

        Customer customer1 = Customer.builder()
                .name(Name.builder()
                        .firstName("John")
                        .lastName("Thompson")
                        .build())
                .billToAddress(address1)
                .shipToAddress(address1)
                .email("john@springframework.guru")
                .phone("800-555-1212")
                .paymentMethods(List.of(PaymentMethod.builder()
                        .displayName("My Card")
                        .cardNumber(12341234)
                        .expiryMonth(12)
                        .expiryYear(26)
                        .cvv(123)
                        .build()))
                .build();

        Address address2 = Address.builder()
                .addressLine1("1234 W Some Street")
                .city("Some City")
                .state("FL")
                .zip("33701")
                .build();

        Customer customer2 = Customer.builder()
                .name(Name.builder()
                        .firstName("Jim")
                        .lastName("Smith")
                        .build())
                .billToAddress(address2)
                .shipToAddress(address2)
                .email("jim@springframework.guru")
                .phone("800-555-1212")
                .paymentMethods(List.of(PaymentMethod.builder()
                        .displayName("My Other Card")
                        .cardNumber(1234888)
                        .expiryMonth(12)
                        .expiryYear(26)
                        .cvv(456)
                        .build()))
                .build();

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        Product product1 = Product.builder()
                .description("Product 1")
                .categories(List.of(Category.builder()
                        .category("Category 1")
                        .description("Category 1 Description")
                        .build()))
                .cost("12.99")
                .price("14.99")
                .dimensions(Dimensions.builder()
                        .height(1)
                        .length(2)
                        .width(3)
                        .build())
                .images(List.of(Image.builder()
                        .url("http://example.com/image1")
                        .altText("Image 1")
                        .build()))
                .build();

        Product product2 = Product.builder()
                .description("Product 2")
                .categories(List.of(Category.builder()
                        .category("Category 2")
                        .description("Category 2 Description")
                        .build()))
                .cost("12.99")
                .price("14.99")
                .dimensions(Dimensions.builder()
                        .height(1)
                        .length(2)
                        .width(3)
                        .build())
                .images(List.of(Image.builder()
                        .url("http://example.com/image2")
                        .altText("Image 2")
                        .build()))
                .build();

        productRepository.save(product1);
        productRepository.save(product2);
    }
}
