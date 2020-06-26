package com.shopping.basket.implementation;

import com.shopping.basket.dto.CustomerDTO;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.Map;

public interface ICustomerService {
    @Transactional
    boolean registerCustomer(CustomerDTO customerDTO);

    @Transactional
    Map<Object, Object> loginCustomer(CustomerDTO customerDTO);
}
