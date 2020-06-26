package com.shopping.basket.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shopping.basket.entity.base.MainBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "basket_item")
public class BasketItemEntity extends MainBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_item_id")
    private Integer					basketItemId;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

    //Parent entity
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private BasketEntity basket;

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

    public BasketEntity getBasket() {
        return basket;
    }

    public void setBasket(BasketEntity basket) {
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "BasketItemEntity{" +
                "basketItemId=" + basketItemId +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", basket=" + basket +
                '}';
    }
}
