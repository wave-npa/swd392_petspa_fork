package org.petspa.petcaresystem.customer.repository;

import org.petspa.petcaresystem.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
