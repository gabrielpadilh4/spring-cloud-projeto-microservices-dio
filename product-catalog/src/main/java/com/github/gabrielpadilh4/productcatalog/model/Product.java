package com.github.gabrielpadilh4.productcatalog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "product")
public class Product {
    @Id
    private long id;
    private String name;
    private int amount;
}
