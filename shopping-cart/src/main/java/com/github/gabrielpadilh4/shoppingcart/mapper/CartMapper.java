package com.github.gabrielpadilh4.shoppingcart.mapper;

import com.github.gabrielpadilh4.shoppingcart.dto.request.CartDTO;
import com.github.gabrielpadilh4.shoppingcart.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    Cart toModel(CartDTO cartDTO);

    CartDTO toDTO(Cart cart);
}
