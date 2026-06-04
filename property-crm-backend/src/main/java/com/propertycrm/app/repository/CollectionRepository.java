package com.propertycrm.app.repository;

import com.propertycrm.app.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CollectionRepository
        extends JpaRepository<Collection, Long> {

    @Query("""
            SELECT COALESCE(SUM(c.amount),0)
            FROM Collection c
           """)
    Double getTotalCollection();
}