package com.github.jnglman.demo.first.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Document {
    @NotEmpty
    @Length(min = 9, max = 9)
    @Digits(integer = 9, fraction = 0)
    private String seller;
    @NotEmpty
    @Length(min = 9, max = 9)
    @Digits(integer = 9, fraction = 0)
    private String customer;
    @Valid
    @NotEmpty
    private List<Product> products;

    public Document() {
    }

    public Document(String seller, String customer, List<Product> products) {
        this.seller = seller;
        this.customer = customer;
        this.products = products;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
