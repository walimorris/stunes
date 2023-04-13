package com.morris.stunes.controller;

import com.morris.stunes.model.Customer;
import com.morris.stunes.repository.AuroraCustomerRepository;
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

    @GetMapping("/performance")
    public String getPerformanceMetrics() {
        return "performance";
    }

    @PostMapping("/performance/topfivecustomers")
    public String getTopFiveCustomers(Model model) {
        List<Customer> topFiveCustomersList = auroraCustomerRepository.findByTopFiveCustomers();
        model.addAttribute("topfive", topFiveCustomersList);
        return "performanceresult";
    }
}
