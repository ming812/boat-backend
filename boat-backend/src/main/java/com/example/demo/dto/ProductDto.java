package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private String price;
    private String discount;
    private String category;
    private String qty;
    private String description;
    private String mainImage;
    private List<String> subImages;
    private String createUser;
    private Date createTime;
    private String lastModifiedUser;
    private Date lastModifiedTime;
}
