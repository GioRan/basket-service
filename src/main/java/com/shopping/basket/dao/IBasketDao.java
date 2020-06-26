package com.shopping.basket.dao;

import com.shopping.basket.entity.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("basketDao")
public interface IBasketDao extends JpaRepository<BasketEntity, Integer> {

    List<BasketEntity> findByCustomerId(Integer customerId);
}
