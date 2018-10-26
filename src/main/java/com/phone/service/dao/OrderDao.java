package com.phone.service.dao;

import com.phone.domain.Order;
import com.phone.domain.OrderPhone;
import com.phone.domain.repository.OrderPhoneRepository;
import com.phone.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderPhoneRepository orderPhoneRepository;

    public Order insertOrder(Order order) {
        orderRepository.save(order);
        for(OrderPhone orderPhone : order.getOrderPhones()) {
            orderPhoneRepository.save(orderPhone);
        }
        return order;
    }
}
