package com.shopping.basket.rest.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.shopping.basket.dto.BasketDTO;
import com.shopping.basket.implementation.IBasketService;
import com.shopping.basket.implementation.ICustomerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/v1/basket")
public class RestCtrlBasket {

    @Autowired
    private IBasketService basketService;

    @GetMapping("/{basketId}")
    public ResponseEntity<BasketDTO> getBasketByBasketId(@PathVariable Integer basketId){
        BasketDTO basketDTO = basketService.getBasketByBasketId(basketId);

        return ResponseEntity.ok(basketDTO);
    }

    @GetMapping
    public ResponseEntity<List<BasketDTO>> getBasketsByCustomerId(@RequestParam Integer customerId){
        List<BasketDTO> basketDTOList = basketService.getBasketsByCustomerId(customerId);

        return ResponseEntity.ok(basketDTOList);
    }

    @GetMapping("/totalPrice")
    public ResponseEntity<HashMap<String, Integer>> getSumOfAllItemsInAllBasketsByCustomerId(@RequestParam Integer customerId){
        HashMap<String, Integer> overallTotal = basketService.getSumOfAllItemsInAllBasketsByCustomerId(customerId);

        return ResponseEntity.ok(overallTotal);
    }

    @PostMapping
    public ResponseEntity<BasketDTO> addBasket(@RequestBody BasketDTO basketDTO){
        basketDTO = basketService.addBasket(basketDTO);

        return ResponseEntity.ok(basketDTO);
    }

    @PutMapping
    public ResponseEntity<BasketDTO> updateBasket(@RequestBody BasketDTO basketDTO){
        basketDTO = basketService.updateBasket(basketDTO);

        return ResponseEntity.ok(basketDTO);
    }

    @DeleteMapping("/{basketId}")
    public ResponseEntity<Boolean> deleteBasketByBasketId(@PathVariable Integer basketId){
        Boolean status = basketService.deleteBasketByBasketId(basketId);

        return ResponseEntity.ok(status);
    }
}
