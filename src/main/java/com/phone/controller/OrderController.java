package com.phone.controller;

import com.phone.model.OrderRequest;
import com.phone.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @ApiOperation(value = "create order with customer information and list of phones that the customer want to buy")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Order created"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PostMapping("/order")
    public String createOrder(@RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }
}
