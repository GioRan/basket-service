package com.shopping.basket.dto;

import com.shopping.basket.dto.base.MainBaseDTO;

import java.util.List;

public class BasketDTO extends MainBaseDTO {
    private Integer basketId;
    private Integer customerId;

    //Child entities
    private List<BasketItemDTO> items;

    public Integer getBasketId() {
        return basketId;
    }

    public void setBasketId(Integer basketId) {
        this.basketId = basketId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<BasketItemDTO> getItems() {
        return items;
    }

    public void setItems(List<BasketItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "BasketDTO{" +
                "basketId=" + basketId +
                ", customerId=" + customerId +
                ", items=" + items +
                '}';
    }
}
