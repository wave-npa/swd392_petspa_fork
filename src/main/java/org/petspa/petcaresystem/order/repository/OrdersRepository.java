package org.petspa.petcaresystem.order.repository;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.order.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<UserOrder, Long>{

    public UserOrder findByUserOrderId(Long userOrderId);
    public List<UserOrder> findAllByCustomer(AuthenUser authenUser);
}
