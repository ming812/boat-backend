package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadProduct(
            @RequestParam("userId") String userId,
            @RequestBody ProductDto productDto){

        return ResponseEntity.ok(productService.uploadProduct(Long.parseLong(userId) , productDto));

        // Example response
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(1).toUri();

//        return ResponseEntity.created(location).body("Product uploaded successfully");
    }
}
