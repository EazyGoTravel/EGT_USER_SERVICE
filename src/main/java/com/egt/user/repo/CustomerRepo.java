package com.egt.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egt.user.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
