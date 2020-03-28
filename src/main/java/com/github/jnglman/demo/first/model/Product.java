package com.github.jnglman.demo.first.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

public class Product {
    @NotEmpty
    private String name;
    @NotEmpty
    @Length(min = 13, max = 13)
    @Digits(integer = 13, fraction = 0)
    private String code;

    public Product() {
    }

    public Product(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
