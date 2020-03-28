package com.github.jnglman.demo.first.model;

import java.util.List;

public class Errors {
    private List<String> errors;

    public Errors() {
    }

    public Errors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
