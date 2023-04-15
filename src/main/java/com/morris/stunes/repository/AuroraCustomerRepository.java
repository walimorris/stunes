package com.morris.stunes.repository;

import com.morris.stunes.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuroraCustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT c.CustomerId, c.FirstName, c.LastName, c.Company, c.Address, c.City, c.State," +
            " c.Country, c.PostalCode, c.Phone, c.Fax, c.Email, c.SupportRepId FROM customers as c JOIN " +
            "invoices as i ON c.CustomerId = i.CustomerId GROUP BY c.CustomerId ORDER BY SUM(i.Total)" +
            " DESC LIMIT 5;",
           nativeQuery = true)
    List<Customer> findByTopFiveCustomers();

    @Query(value = "SELECT c.CustomerId, c.FirstName, c.LastName, c.Company, c.Address, c.City, c.State," +
            "c.Country, c.PostalCode, c.Phone, c.Fax, c.Email, c.SupportRepId FROM customers as c JOIN" +
            " invoices as i ON c.CustomerId = i.CustomerId ORDER BY Total DESC LIMIT 10;",
           nativeQuery = true)
    List<Customer> findCustomersByLargestInvoiceTotals();
}
