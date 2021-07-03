package com.github.gabrielpadilh4.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("cart")
public class Cart {

    @Id
    private long id;
    private List<Item> items;
}
