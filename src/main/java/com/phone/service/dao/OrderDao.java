package com.phone.service.dao;

import com.phone.domain.Order;
import com.phone.domain.OrderPhone;
import com.phone.domain.repository.OrderPhoneRepository;
import com.phone.domain.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class OrderDao {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderPhoneRepository orderPhoneRepository;

    public Order insertOrder(Order order) {
        orderRepository.save(order);
        for(OrderPhone item : order.getOrderPhones()) {
            orderPhoneRepository.save(item);
        }
        log.info("save order: {}", order);
        return order;
    }
}
