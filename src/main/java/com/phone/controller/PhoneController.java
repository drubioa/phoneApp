package com.phone.controller;

import com.phone.service.PhoneService;
import com.phone.model.PhoneDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
@Slf4j
public class PhoneController {

    @Autowired
    private PhoneService phoneService;


    @ApiOperation(value = "get phones catalog")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Customer Details Retrieved"),
            @ApiResponse(code=500, message = "Internal Server Error")})
    @GetMapping("/phone")
    public List<PhoneDto> getPhonesCatalog(){
        return phoneService.getPhoneCatalog();
    }

}
