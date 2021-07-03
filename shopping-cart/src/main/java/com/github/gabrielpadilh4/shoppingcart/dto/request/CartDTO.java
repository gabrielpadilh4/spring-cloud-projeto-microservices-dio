package com.github.gabrielpadilh4.shoppingcart.dto.request;

import com.github.gabrielpadilh4.shoppingcart.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private long id;
    private List<Item> items;
}
