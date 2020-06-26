package com.shopping.basket.dao;

import com.shopping.basket.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("customerDao")
public interface ICustomerDao extends JpaRepository<CustomerEntity, Integer> {

    CustomerEntity findByUsername(String username);

    CustomerEntity findByUsernameAndPassword(String username, String password);
}
