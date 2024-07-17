package org.petspa.petcaresystem.order.service;

import java.util.Collection;

import org.petspa.petcaresystem.enums.ReviewRating;
import org.petspa.petcaresystem.order.model.UserOrder;
import org.petspa.petcaresystem.pet.model.request.CreatePetRequest;
import org.petspa.petcaresystem.pet.model.request.UpdatePetRequest;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Collection<UserOrder> findAllOrder();

    UserOrder findOrderById(Long orderId);

    UserOrder saveOrder(UserOrder order);

    UserOrder updateOrder(UserOrder order);

    Collection<UserOrder> findOrderByUser(Long userId);

}
