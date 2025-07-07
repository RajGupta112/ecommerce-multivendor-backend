package com.javafullstackEcommerce.ecommerce.controller;

import com.javafullstackEcommerce.ecommerce.Exception.ProductException;
import com.javafullstackEcommerce.ecommerce.Exception.SellerException;
import com.javafullstackEcommerce.ecommerce.modal.Product;
import com.javafullstackEcommerce.ecommerce.modal.Seller;
import com.javafullstackEcommerce.ecommerce.response.CreateProductReqest;
import com.javafullstackEcommerce.ecommerce.service.ProductService;
import com.javafullstackEcommerce.ecommerce.service.SellerService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/sellers/products")
@RequiredArgsConstructor
public class SellerProductController {

    private final ProductService productService;

    private final SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Product>> getProductBySellerId(@RequestHeader("Authorization") String jwt) throws Exception {
        Seller seller=sellerService.getSellerProfile(jwt);

        List<Product> products=productService.getProductBySellerId(seller.getId());

        return new ResponseEntity<>(products, HttpStatus.OK);


    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductReqest request,
                                                 @RequestHeader("Authorization") String jwt
                                                 ) throws Exception {
        Seller seller=sellerService.getSellerProfile(jwt);
        Product product=productService.createProduct(request,seller);
        return  new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) throws ProductException {
        try{
       productService.deleteProduct(productId);
       return new ResponseEntity<>(HttpStatus.OK);
        }catch (ProductException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@RequestBody Product product) throws ProductException {
        try{
            Product product1=productService.updateProduct(productId,product);
            return  new ResponseEntity<>(product1,HttpStatus.OK);
        }catch (ProductException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
