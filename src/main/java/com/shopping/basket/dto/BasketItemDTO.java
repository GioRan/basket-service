package com.shopping.basket.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopping.basket.dto.base.MainBaseDTO;

public class BasketItemDTO extends MainBaseDTO {
    private Integer basketItemId;
    private String name;
    private Integer quantity;
    private Integer price;

    //Parent entity
    @JsonIgnore
    private Integer basketId;

    public Integer getBasketItemId() {
        return basketItemId;
    }

    public void setBasketItemId(Integer basketItemId) {
        this.basketItemId = basketItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getBasketId() {
        return basketId;
    }

    public void setBasketId(Integer basketId) {
        this.basketId = basketId;
    }

    @Override
    public String toString() {
        return "BasketItemDTO{" +
                "basketItemId=" + basketItemId +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", basketId=" + basketId +
                '}';
    }
}
