package org.petspa.petcaresystem.order.service.implement;

import java.util.Collection;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.order.model.UserOrder;
import org.petspa.petcaresystem.order.repository.OrdersRepository;
import org.petspa.petcaresystem.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    AuthenUserRepository authenUserRepository;

    @Override
    public Collection<UserOrder> findAllOrder() {
        return ordersRepository.findAll();
    }

    @Override
    public UserOrder findOrderById(Long orderId) {
       return ordersRepository.findByUserOrderId(orderId);
    }

    @Override
    public UserOrder saveOrder(UserOrder order) {
        return ordersRepository.save(order);
    }

    @Override
    public UserOrder updateOrder(UserOrder order) {
        return ordersRepository.save(order);
    }

    @Override
    public Collection<UserOrder> findOrderByUser(Long userId) {
        AuthenUser user = authenUserRepository.findById(userId).orElse(null);
        return ordersRepository.findAllByCustomer(user);
    }

}
