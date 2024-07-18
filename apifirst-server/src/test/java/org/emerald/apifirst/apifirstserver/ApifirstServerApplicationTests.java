package org.emerald.apifirst.apifirstserver;

import org.emerald.apifirst.apifirstserver.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ApifirstServerApplicationTests {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testDataLoad() {
        assertThat(customerRepository.count()).isGreaterThan(0L);
    }

}
