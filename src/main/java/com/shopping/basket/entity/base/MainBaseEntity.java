package com.shopping.basket.entity.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@MappedSuperclass
public abstract class MainBaseEntity implements Serializable {

    @Column(name = "when_added")
    private Long				whenAdded;



    @Column(name = "when_updated")
    private Long				whenUpdated;

;

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



    @PrePersist
    public void prePersist() {
        LocalDateTime time = LocalDateTime.now();
        whenAdded= time.toEpochSecond(ZoneOffset.UTC);
    }

    @PreUpdate
    public void preUpdate() {
        LocalDateTime time = LocalDateTime.now();
        whenUpdated = time.toEpochSecond(ZoneOffset.UTC);
    }

    @Override
    public String toString() {
        return "MainBaseEntity{" +
                "whenAdded=" + whenAdded +
                ", whenUpdated=" + whenUpdated +
                '}';
    }
}