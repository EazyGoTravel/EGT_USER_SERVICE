package com.egt.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egt.user.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {

}
