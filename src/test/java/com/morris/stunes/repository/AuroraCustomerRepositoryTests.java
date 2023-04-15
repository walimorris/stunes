package com.morris.stunes.repository;

import com.morris.stunes.configuration.SpringDataConfiguration;
import com.morris.stunes.model.Customer;
import com.morris.stunes.model.Employee;
import com.morris.stunes.model.Invoice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(value = SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class AuroraCustomerRepositoryTests {

    @Autowired
    AuroraCustomerRepository auroraCustomerRepository;

    @Autowired
    AuroraInvoiceRepository auroraInvoiceRepository;

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

    @Test
    public void findCustomersByLargestInvoiceTotalsTest() {
        List<Customer> customersWithLargestInvoiceTotalsList = auroraCustomerRepository.findCustomersByLargestInvoiceTotals();
        List<Invoice> topTenLargestInvoiceTotalsList = auroraInvoiceRepository.findTopTenLargestInvoiceTotals();

        // Top to totals will always be the same, though some customers may have the same total.
        assertAll(
                () -> assertNotNull(customersWithLargestInvoiceTotalsList),
                () -> assertNotNull(topTenLargestInvoiceTotalsList),
                () -> assertEquals(10, customersWithLargestInvoiceTotalsList.size()),
                () -> assertEquals(10, topTenLargestInvoiceTotalsList.size())
        );
    }
}
