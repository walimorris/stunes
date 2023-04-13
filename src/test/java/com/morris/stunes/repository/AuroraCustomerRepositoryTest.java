package com.morris.stunes.repository;

import com.morris.stunes.configuration.SpringDataConfiguration;
import com.morris.stunes.model.Customer;
import com.morris.stunes.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(value = SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class AuroraCustomerRepositoryTest {

    @Autowired
    AuroraCustomerRepository auroraCustomerRepository;

    @Test
    public void findTopFiveCustomersTest() {
        List<Customer> topFiveCustomers = auroraCustomerRepository.findByTopFiveCustomers();

        // check that Employee Object on Customer is valid
        for (Customer customer : topFiveCustomers) {
            assertInstanceOf(Employee.class, customer.getEmployeeSupportRep());
        }
        assertAll(
                () -> assertNotNull(topFiveCustomers),
                () -> assertEquals(5, topFiveCustomers.size())
        );
    }
}
