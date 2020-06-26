package com.shopping.basket.service;

import com.shopping.basket.dao.ICustomerDao;
import com.shopping.basket.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customerAuthDetailsService")
public class CustomerAuthDetailsService implements UserDetailsService {

    @Autowired
    private ICustomerDao customerDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerEntity customerEntity = customerDao.findByUsername(username);

        if(customerEntity == null) throw new UsernameNotFoundException("Username: " + username + " not found");

        return customerEntity;
    }
}
