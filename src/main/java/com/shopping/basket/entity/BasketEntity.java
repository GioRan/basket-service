package com.shopping.basket.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shopping.basket.entity.base.MainBaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "basket")
public class BasketEntity extends MainBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_id")
    private Integer					basketId;

    @Column(name = "customer_id")
    private Integer customerId;

    //Child entities
    @JsonBackReference
    @OneToMany(mappedBy = "basket", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<BasketItemEntity> items;

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

    public List<BasketItemEntity> getItems() {
        return items;
    }

    public void setItems(List<BasketItemEntity> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "BasketEntity{" +
                "basketId=" + basketId +
                ", customerId=" + customerId +
                ", items=" + items +
                '}';
    }
}
