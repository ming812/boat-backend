package com.example.demo.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
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
@Entity
@Table(name = "product_image")
public class ProductImage {

    @Id
    @Column(name= "product_id")
    private Long productId;

    @Column(name = "main_image")
    private String mainImage;

    @Nullable
    @Column(name = "sub_image_1")
    private String subImage;

    @Nullable
    @Column(name = "sub_image_2")
    private String subImage2;

    @Nullable
    @Column(name = "sub_image_3")
    private String subImage3;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_modified_user")
    private String lastModifiedUser;

    @Column(name = "last_modified_time")
    private Date lastModifiedTime;
}
