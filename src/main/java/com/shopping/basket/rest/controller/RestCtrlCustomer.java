package com.shopping.basket.rest.controller;

import com.shopping.basket.dto.CustomerDTO;
import com.shopping.basket.implementation.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/public")
public class RestCtrlCustomer {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerDTO customerDTO){
        Boolean isRegistered = customerService.registerCustomer(customerDTO);

        if(!isRegistered) return ResponseEntity.ok("Username already given");

        return ResponseEntity.ok("Successfully Registered. Please login at http://localhost:8080/api/public/login");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> loginCustomer(@RequestBody CustomerDTO customerDTO){
        Map<Object, Object> loginDetails = customerService.loginCustomer(customerDTO);

        return ResponseEntity.ok(loginDetails);
    }
}
