package com.github.gabrielpadilh4.productcatalog.service;

import com.github.gabrielpadilh4.productcatalog.dto.request.ProductDTO;
import com.github.gabrielpadilh4.productcatalog.dto.response.MessageResponseDTO;
import com.github.gabrielpadilh4.productcatalog.exception.ProductNotFoundException;
import com.github.gabrielpadilh4.productcatalog.mapper.ProductMapper;
import com.github.gabrielpadilh4.productcatalog.model.Product;
import com.github.gabrielpadilh4.productcatalog.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private ProductRepository productRepository;

    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product productToSave = productMapper.toModel(productDTO);
        Product savedProduct = productRepository.save(productToSave);

        return productMapper.toDTO(savedProduct);
    }

    public List<ProductDTO> listAll() {

        List<ProductDTO> productDTOList = new ArrayList<>();

        Iterable<Product> products = productRepository.findAll();
        products.forEach(p -> productDTOList.add(productMapper.toDTO(p)));

        return productDTOList;
    }

    public ProductDTO findById(long id) throws ProductNotFoundException {
        Product product = verifyIfProductExists(id);
        return productMapper.toDTO(product);
    }

    public void delete(long id) throws ProductNotFoundException {
        verifyIfProductExists(id);
        productRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(long id, ProductDTO productDTO) throws ProductNotFoundException {
        verifyIfProductExists(id);

        Product productToUpdate = productMapper.toModel(productDTO);
        Product updatedProduct = productRepository.save(productToUpdate);

        return createMessageResponse(id, "Updated product with id ");
    }

    private Product verifyIfProductExists(long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(long id, String message) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }

}
