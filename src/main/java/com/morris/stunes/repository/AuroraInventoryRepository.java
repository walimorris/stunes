package com.morris.stunes.repository;

import com.morris.stunes.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuroraInventoryRepository extends JpaRepository<Invoice, Integer> {

    @Query(value = "SELECT i.InvoiceId, i.CustomerId,i.InvoiceDate,i.BillingAddress," +
            "i.BillyCity,i.BillingState,i.BillingCountry,i.BillingPostalCode,SUM(i.Total)" +
            " as 'Total'FROM invoices AS i JOIN customers AS c ON i.CustomerId = c.CustomerId" +
            " JOIN employees As e ON c.SupportRepId = e.EmployeeId Group BY e.EmployeeId " +
            "ORDER BY SUM(i.Total) DESC;",
           nativeQuery = true)
    List<Invoice> findBySumOfTopInvoicesByEmployeeDescending();
}
