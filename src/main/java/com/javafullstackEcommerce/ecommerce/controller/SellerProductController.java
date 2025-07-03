package com.javafullstackEcommerce.ecommerce.controller;

import com.javafullstackEcommerce.ecommerce.modal.Product;
import com.javafullstackEcommerce.ecommerce.service.ProductService;
import com.javafullstackEcommerce.ecommerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/sellers/products")
@RequiredArgsConstructor
public class SellerProductController {

    private final ProductService productService;

    private final SellerService sellerService;


}
