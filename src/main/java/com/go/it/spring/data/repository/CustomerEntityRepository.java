package com.go.it.spring.data.repository;

import com.go.it.spring.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findCustomerById(Long id);
}
