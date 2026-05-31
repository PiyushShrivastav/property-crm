package com.propertycrm.app.repository;

import com.propertycrm.app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository
        extends JpaRepository<Customer, Long> {
}