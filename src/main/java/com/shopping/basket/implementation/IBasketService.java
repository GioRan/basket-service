package com.shopping.basket.implementation;

import com.shopping.basket.dto.BasketDTO;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

public interface IBasketService {
    @Transactional
    BasketDTO getBasketByBasketId(Integer basketId);

    @Transactional
    List<BasketDTO> getBasketsByCustomerId(Integer customerId);

    @Transactional
    HashMap<String, Integer> getSumOfAllItemsInAllBasketsByCustomerId(Integer customerId);

    @Transactional
    BasketDTO addBasket(BasketDTO basketDTO);

    @Transactional
    BasketDTO updateBasket(BasketDTO basketDTO);

    @Transactional
    boolean deleteBasketByBasketId(Integer basketId);
}
