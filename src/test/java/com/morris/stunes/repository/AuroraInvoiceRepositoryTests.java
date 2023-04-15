package com.morris.stunes.repository;

import com.morris.stunes.configuration.SpringDataConfiguration;
import com.morris.stunes.model.Invoice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class AuroraInvoiceRepositoryTests {

    @Autowired
    AuroraInvoiceRepository auroraInventoryRepository;

    @Test
    public void findBySumOfTopInvoicesByEmployeeDescendingTest() {
        List<Invoice> topEmployeeInvoicesTotalList = auroraInventoryRepository
                .findBySumOfTopInvoicesByEmployeeDescending();

        assertAll(
                () -> assertNotNull(topEmployeeInvoicesTotalList),
                () -> assertEquals(3, topEmployeeInvoicesTotalList.size())
        );
    }

    @Test
    public void findTopTenLargestInvoiceTotals() {
        List<Invoice> topTenInvoiceTotalsList = auroraInventoryRepository.findTopTenLargestInvoiceTotals();

        assertAll(
                () -> assertNotNull(topTenInvoiceTotalsList),
                () -> assertEquals(10, topTenInvoiceTotalsList.size())
        );
    }
}
