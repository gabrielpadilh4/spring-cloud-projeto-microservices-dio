package com.github.gabrielpadilh4.shoppingcart.controller;

import com.github.gabrielpadilh4.shoppingcart.dto.request.CartDTO;
import com.github.gabrielpadilh4.shoppingcart.dto.response.MessageResponseDTO;
import com.github.gabrielpadilh4.shoppingcart.exception.CartNotFoundException;
import com.github.gabrielpadilh4.shoppingcart.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CartController {

    CartService cartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartDTO createCart(@RequestBody CartDTO cartDTO) {
        return cartService.createCart(cartDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CartDTO> listAll() {
        return cartService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CartDTO findById(@PathVariable("id") long id) throws CartNotFoundException {
        return cartService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable("id") long id, @RequestBody CartDTO cartDTO) throws CartNotFoundException {
        return cartService.updateById(id, cartDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") long id) throws CartNotFoundException {
        cartService.delete(id);
    }


}
