package com.shopping.basket.dto.base;

public abstract class MainBaseDTO {

    private Long		whenAdded;
    private Long		whenUpdated;

    public Long getWhenAdded() {
        return whenAdded;
    }

    public void setWhenAdded(Long whenAdded) {
        this.whenAdded = whenAdded;
    }


    public Long getWhenUpdated() {
        return whenUpdated;
    }

    public void setWhenUpdated(Long whenUpdated) {
        this.whenUpdated = whenUpdated;
    }


    @Override
    public String toString() {
        return "MainBaseDTO{" +
                "whenAdded=" + whenAdded +
                ", whenUpdated=" + whenUpdated +
                '}';
    }
}