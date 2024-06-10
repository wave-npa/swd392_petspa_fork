package org.petspa.petcaresystem.order.repository;

import org.petspa.petcaresystem.order.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String>{
}
