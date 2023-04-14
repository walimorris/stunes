package com.morris.stunes.repository;

import com.morris.stunes.configuration.SpringDataConfiguration;
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
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class AuroraEmployeeRepositoryTests {

    @Autowired
    AuroraEmployeeRepository auroraEmployeeRepository;

    @Test
    public void findTopFiveEmployeesTest() {
        List<Employee> employees = auroraEmployeeRepository.findByTopFiveEmployeesDescending();

        assertAll(
                () -> assertTrue(employees.size() > 1),
                () -> assertEquals(3, employees.size())
        );
    }

    @Test
    public void findEmployeeByEmployeeId() {
        List<Employee> employees = auroraEmployeeRepository.findByTopFiveEmployeesDescending();

        for (Employee employee : employees) {
            Employee e = auroraEmployeeRepository.findEmployeeByEmployeeId(employee.getEmployeeId());

            assertAll(
                    () -> assertNotNull(e),
                    () -> assertNotNull(e.getFirstName()),
                    () -> assertInstanceOf(Employee.class, e)
            );
        }
    }
}
