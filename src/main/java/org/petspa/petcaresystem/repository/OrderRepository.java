package org.petspa.petcaresystem.repository;

import org.petspa.petcaresystem.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface OrderRepository extends JpaRepository<Order, Integer>{
}
