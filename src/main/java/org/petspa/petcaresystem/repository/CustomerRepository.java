package org.petspa.petcaresystem.repository;

import org.petspa.petcaresystem.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
