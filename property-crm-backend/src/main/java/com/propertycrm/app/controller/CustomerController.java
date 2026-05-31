package com.propertycrm.app.controller;

import com.propertycrm.app.dto.response.CustomerResponse;
import com.propertycrm.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/convert/{leadId}")
    public CustomerResponse convertLead(
            @PathVariable Long leadId) {

        return customerService.convertLead(leadId);
    }

    @GetMapping
    public List<CustomerResponse> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomer(
            @PathVariable Long id) {

        return customerService.getCustomer(id);
    }
}