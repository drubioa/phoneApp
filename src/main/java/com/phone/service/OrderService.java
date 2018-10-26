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
import java.time.Instant;
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

    @Autowired
    private ValidatorComponent validatorComponent;

    /**
     *
     * @param orderRequest customer information  with items which going to buy.
     * @return created order identifier.
     *
     */
    @Transactional
    public String createOrder(OrderRequest orderRequest) {
        validateCustomer(orderRequest.getCustomerDto());
        Order newOrder = orderRequestToOrder(orderRequest);
        newOrder.setCreationDate(Instant.now());
        newOrder = orderDao.insertOrder(newOrder);
        log.info("Save new order {}", newOrder.getId());
        return newOrder.getId().toString();
    }

    /**
     * validate customer contact info.
     *
     * @param customerDto
     */
    private void validateCustomer(CustomerDto customerDto) {
        if(!validatorComponent.validateEmailAddress(customerDto.getEmail())) {
            log.info("Invalid customer emil address {}", customerDto.getEmail());
            throw new IllegalArgumentException("Invalid email address: "+ customerDto.getEmail());
        }
        if(!validatorComponent.validatePhoneNumber(customerDto.getPhoneNumber())) {
            log.info("Invalid customer phone number {}", customerDto.getPhoneNumber());
            throw new IllegalArgumentException("Invalid phone number: {}"+ customerDto.getPhoneNumber());
        }

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
                log.info("Cannot obtains phone with id {}", it.getPhoneId());
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
