package com.shopping.basket.service;

import com.shopping.basket.dao.ICustomerDao;
import com.shopping.basket.dto.CustomerDTO;
import com.shopping.basket.entity.CustomerEntity;
import com.shopping.basket.implementation.ICustomerService;
import com.shopping.basket.security.jwt.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service("customerService")
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerDao customerDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public boolean registerCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerDao.findByUsername(customerDTO.getUsername());

        if(customerEntity != null) return false;

        customerEntity = customerDao.save(CustomerEntity.builder().username(customerDTO.getUsername()).password(passwordEncoder.encode(customerDTO.getPassword())).build());

        return true;
    }

    @Override
    @Transactional
    public Map<Object, Object> loginCustomer(CustomerDTO customerDTO){
        try {
            String username = customerDTO.getUsername();
            String password = customerDTO.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            String				token	= createToken(username);

            Map<Object, Object> loginDetails	= new HashMap<>();
            loginDetails.put("username", username);
            loginDetails.put("token", token);

            return loginDetails;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    private String createToken(String username) {
        CustomerEntity customerEntity = customerDao.findByUsername(username);

        if(customerEntity == null) throw new UsernameNotFoundException("Username " + username + "not found");

        String token = jwtTokenProvider.createToken(username);

        return token;
    }
}
