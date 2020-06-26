package com.shopping.basket.dto;

import com.shopping.basket.dto.base.MainBaseDTO;

import javax.validation.constraints.NotEmpty;

public class CustomerDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
