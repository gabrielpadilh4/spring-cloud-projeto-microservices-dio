package com.github.gabrielpadilh4.shoppingcart.repository;

import com.github.gabrielpadilh4.shoppingcart.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
}
