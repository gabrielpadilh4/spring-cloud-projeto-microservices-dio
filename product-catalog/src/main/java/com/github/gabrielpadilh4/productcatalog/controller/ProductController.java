package com.github.gabrielpadilh4.productcatalog.controller;

import com.github.gabrielpadilh4.productcatalog.dto.request.ProductDTO;
import com.github.gabrielpadilh4.productcatalog.dto.response.MessageResponseDTO;
import com.github.gabrielpadilh4.productcatalog.exception.ProductNotFoundException;
import com.github.gabrielpadilh4.productcatalog.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> listAll() {
        return productService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO findById(@PathVariable("id") long id) throws ProductNotFoundException {
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable("id") long id, @RequestBody ProductDTO productDTO) throws ProductNotFoundException {
        return productService.updateById(id, productDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") long id) throws ProductNotFoundException {
        productService.delete(id);
    }
}
