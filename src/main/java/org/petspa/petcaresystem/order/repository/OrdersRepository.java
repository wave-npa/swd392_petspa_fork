package org.petspa.petcaresystem.order.repository;

import org.petspa.petcaresystem.order.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<UserOrder, Long>{
}
