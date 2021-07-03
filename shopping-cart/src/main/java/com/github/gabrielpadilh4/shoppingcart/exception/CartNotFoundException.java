package com.github.gabrielpadilh4.shoppingcart.exception;

public class CartNotFoundException extends Exception {
    public CartNotFoundException(long id) {
        super("Cart not found with id " + id);
    }
}
