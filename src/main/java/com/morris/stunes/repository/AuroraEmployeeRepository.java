package com.morris.stunes.repository;

import com.morris.stunes.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuroraEmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "SELECT e.EmployeeId, e.FirstName, e.LastName, e.Title, e.ReportsTo," +
            " e.BirthDate, e.HireDate, e.Address, e.City, e.State, e.Country, e.PostalCode, " +
            "e.Phone, e.Fax, e.Email FROM employees AS e JOIN customers AS c ON " +
            "e.EmployeeId = c.SupportRepId JOIN invoices AS i ON c.CustomerId = i.CustomerId " +
            "GROUP BY e.EmployeeId ORDER BY SUM(i.Total) DESC",
           nativeQuery = true)
    List<Employee> findByTopFiveEmployeesDescending();

    Employee findEmployeeByEmployeeId(int employeeId);
}
