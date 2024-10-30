package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private String price;

    @Column(name = "discount")
    private String discount;

    @Column(name = "category")
    private String category;

    @Column(name = "qty")
    private String qty;

    @Column(name = "description")
    private String description;

    @Column(name = "main_image")
    private String mainImage;

    @Column(name = "sub_image")
    private List<String> subImage;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_modified_user")
    private String lastModifiedUser;

    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

}
