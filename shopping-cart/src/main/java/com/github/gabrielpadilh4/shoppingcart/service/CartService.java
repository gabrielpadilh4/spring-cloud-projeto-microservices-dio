package com.github.gabrielpadilh4.shoppingcart.service;

import com.github.gabrielpadilh4.shoppingcart.dto.request.CartDTO;
import com.github.gabrielpadilh4.shoppingcart.dto.response.MessageResponseDTO;
import com.github.gabrielpadilh4.shoppingcart.exception.CartNotFoundException;
import com.github.gabrielpadilh4.shoppingcart.mapper.CartMapper;
import com.github.gabrielpadilh4.shoppingcart.model.Cart;
import com.github.gabrielpadilh4.shoppingcart.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CartService {

    private CartRepository cartRepository;

    private final CartMapper cartMapper = CartMapper.INSTANCE;

    public CartDTO createCart(CartDTO cartDTO) {
        Cart cartToSave = cartMapper.toModel(cartDTO);
        Cart savedCart = cartRepository.save(cartToSave);

        return cartMapper.toDTO(savedCart);
    }

    public List<CartDTO> listAll() {
        List<CartDTO> cartDTOList = new ArrayList<>();

        Iterable<Cart> carts = cartRepository.findAll();
        carts.forEach(c -> cartDTOList.add(cartMapper.toDTO(c)));

        return cartDTOList;
    }

    public CartDTO findById(long id) throws CartNotFoundException {
        Cart cart = verifyIfCartExists(id);

        return cartMapper.toDTO(cart);
    }

    public void delete(long id) throws CartNotFoundException {
        verifyIfCartExists(id);
        cartRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(long id, CartDTO cartDTO) throws CartNotFoundException {
        verifyIfCartExists(id);

        Cart cartToUpdate = cartMapper.toModel(cartDTO);
        Cart updatedCart = cartRepository.save(cartToUpdate);

        return createMessageResponse(id, "Updated cart with id");
    }

    private Cart verifyIfCartExists(long id) throws CartNotFoundException {
        return cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(long id, String message) {
        return MessageResponseDTO.builder()
                .message(message + id).build();
    }
}
