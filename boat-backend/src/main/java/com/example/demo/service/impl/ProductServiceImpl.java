package com.example.demo.service.impl;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.GeneralResponse;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductImage;
import com.example.demo.entity.User;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.ProductImageRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public String uploadProduct(Long userId , ProductDto productDto) {
//        MultipartFile mainImage = productDto.getMainImage();
//        List<MultipartFile> subImages = productDto.getSubImages();
        User user = userRepository.findById(userId).orElseThrow(null);

        Product product = ProductMapper.mapToProductWithUpload(user == null ? "" : user.getUserName(), productDto);
        productRepository.save(product);
        productDto.setProductId(product.getProductId());
        ProductImage productImage = ProductMapper.mapToProductImageWithUpload(user == null ? "" : user.getUserName(), productDto);
        productImageRepository.save(productImage);
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setStatusCode(0);
        generalResponse.setMessage("Product uploaded successfully");

        return ProductMapper.serializeObjectToJson(generalResponse);
    }

    @Override
    public ProductDto editProduct(Long userId, ProductDto updatedProduct) {
        return null;
    }

    @Override
    public String loadProductById(Long productId) {
        return "";
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return List.of();
    }

    @Override
    public void deleteProduct(Long productId) {

    }
}
