package com.phone.service;

import com.phone.domain.Order;
import com.phone.domain.OrderPhone;
import com.phone.domain.Phone;
import com.phone.model.CustomerDto;
import com.phone.model.OrderItem;
import com.phone.model.OrderRequest;
import com.phone.service.dao.OrderDao;
import com.phone.service.dao.PhoneDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PhoneDao phoneDao;

    @Transactional
    public String createOrder(OrderRequest orderRequest) {
        Order newOrder = orderRequestToOrder(orderRequest);
        orderDao.insertOrder(newOrder);
        log.info("Save new order {}", newOrder);
        return newOrder.getId().toString();
    }

    private Order orderRequestToOrder(OrderRequest orderRequest) {
        Order order = new Order();
        orderRequestCustomerInfoToOrder(orderRequest, order);
        obtainsItemsAndCalculateTotal(orderRequest, order);
        return order;
    }

    private void obtainsItemsAndCalculateTotal(OrderRequest orderRequest, Order order){
        Double total = 0.;
        List<OrderPhone> orderPhones = new ArrayList<>();
        for(OrderItem it : orderRequest.getItems()) {
            OrderPhone newOrderItem = new OrderPhone();
            Phone phone = phoneDao.findById(UUID.fromString(it.getPhoneId()));
            if(phone == null){
                log.error("Cannot obtains phone with id {}", it.getPhoneId());
                throw new EntityNotFoundException("Cannot obtains phone with id " + it.getPhoneId());
            }
            newOrderItem.setPhone(phone);
            newOrderItem.setOrder(order);
            newOrderItem.setNumber(it.getNum());
            total += phone.getPrice().doubleValue() * it.getNum();
            orderPhones.add(newOrderItem);
        }
        order.setOrderPhones(orderPhones);
        order.setPrice(BigDecimal.valueOf(total));
    }

    private void orderRequestCustomerInfoToOrder(OrderRequest orderRequest, Order order) {
        CustomerDto customer = orderRequest.getCustomerDto();
        order.setCustomerName(customer.getName());
        order.setCustomerFirstSurname(customer.getFirstSurname());
        order.setCustomerSecondSurname(customer.getSecondSurname());
        order.setEmail(customer.getEmail());
        order.setPhoneNumber(customer.getPhoneNumber());
    }
}
