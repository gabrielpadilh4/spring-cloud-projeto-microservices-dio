package com.github.gabrielpadilh4.productcatalog.mapper;

import com.github.gabrielpadilh4.productcatalog.dto.request.ProductDTO;
import com.github.gabrielpadilh4.productcatalog.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toModel(ProductDTO productDTO);

    ProductDTO toDTO(Product product);

}
