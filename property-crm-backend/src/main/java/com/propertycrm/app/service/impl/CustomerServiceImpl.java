package com.propertycrm.app.service.impl;

import com.propertycrm.app.dto.response.CustomerResponse;
import com.propertycrm.app.entity.Customer;
import com.propertycrm.app.entity.Lead;
import com.propertycrm.app.entity.LeadStatus;
import com.propertycrm.app.repository.CustomerRepository;
import com.propertycrm.app.repository.LeadRepository;
import com.propertycrm.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl
        implements CustomerService {

    private final LeadRepository leadRepository;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse convertLead(Long leadId) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException("Lead not found"));

        if (lead.getStatus() != LeadStatus.BOOKED) {

            throw new RuntimeException(
                    "Only BOOKED leads can be converted"
            );
        }

        Customer customer = Customer.builder()
                .customerName(lead.getCustomerName())
                .mobile(lead.getMobile())
                .email(lead.getEmail())
                .city(lead.getCity())
                .lead(lead)
                .createdAt(LocalDateTime.now())
                .build();

        customer = customerRepository.save(customer);

        return map(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public CustomerResponse getCustomer(Long id) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Customer not found"));

        return map(customer);
    }

    private CustomerResponse map(Customer customer) {

        return CustomerResponse.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .mobile(customer.getMobile())
                .email(customer.getEmail())
                .city(customer.getCity())
                .build();
    }
}