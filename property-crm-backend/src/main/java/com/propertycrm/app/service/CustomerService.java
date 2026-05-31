package com.propertycrm.app.service;

import com.propertycrm.app.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse convertLead(Long leadId);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse getCustomer(Long id);
}