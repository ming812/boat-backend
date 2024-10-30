package com.example.demo.service;

import com.example.demo.dto.ProductDto;

import java.util.List;

public interface ProductService {

    String uploadProduct(Long userId , ProductDto productDto);

    ProductDto editProduct(Long userId , ProductDto updatedProduct);

    String loadProductById(Long productId);

    List<ProductDto> getAllProduct();

    void deleteProduct(Long productId);
}
