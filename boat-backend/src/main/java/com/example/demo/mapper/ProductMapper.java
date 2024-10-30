package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ProductMapper extends CommonMapper {

    public static ProductDto mapToProductDto(Product product){
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getPrice(),
                product.getDiscount(),
                product.getCategory(),
                product.getQty(),
                product.getDescription(),
                product.getMainImage(),
                product.getSubImage(),
                null,
                null,
                null,
                null
        );
    }

    public static Product mapToProductWithUpload(String userName , ProductDto productDto){
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return new Product(
                productDto.getProductId(),
                productDto.getProductName(),
                productDto.getPrice(),
                productDto.getDiscount(),
                productDto.getCategory(),
                productDto.getQty(),
                productDto.getDescription(),
                productDto.getMainImage(),
                productDto.getSubImages(),
                userName,
                date,
                userName,
                date
        );
    }
}
