package com.github.gabrielpadilh4.productcatalog.repository;

import com.github.gabrielpadilh4.productcatalog.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {
}
