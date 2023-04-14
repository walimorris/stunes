package com.morris.stunes.controller;

import com.morris.stunes.model.Customer;
import com.morris.stunes.model.Employee;
import com.morris.stunes.model.Invoice;
import com.morris.stunes.repository.AuroraCustomerRepository;
import com.morris.stunes.repository.AuroraEmployeeRepository;
import com.morris.stunes.repository.AuroraInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PerformanceController {

    @Autowired
    AuroraCustomerRepository auroraCustomerRepository;

    @Autowired
    AuroraEmployeeRepository auroraEmployeeRepository;

    @Autowired
    AuroraInventoryRepository auroraInventoryRepository;

    @GetMapping("/performance")
    public String getPerformanceMetrics() {
        return "performance";
    }

    @PostMapping("/performance/topfivecustomers")
    public String getTopFiveCustomers(Model model) {
        List<Customer> topFiveCustomersList = auroraCustomerRepository.findByTopFiveCustomers();
        model.addAttribute("topfivecustomers", topFiveCustomersList);
        return "performanceresult";
    }

    @PostMapping("/performance/topfiveemployees")
    public String getTopFiveEmployees(Model model) {
        List<Employee> topFiveEmployeesList = auroraEmployeeRepository.findByTopFiveEmployeesDescending();
        List<Invoice> topFiveInvoicesList = auroraInventoryRepository.findBySumOfTopInvoicesByEmployeeDescending();
        model.addAttribute("topfiveemployees", topFiveEmployeesList);
        model.addAttribute("topfiveinvoices", topFiveInvoicesList);
        return "performanceresult";
    }
}
