package com.javafullstackEcommerce.ecommerce.service;

import com.javafullstackEcommerce.ecommerce.controller.Seller;
import com.javafullstackEcommerce.ecommerce.domain.AccountStatus;
import org.hibernate.mapping.Selectable;

import java.util.List;

public interface SellerService {
    Seller getSellerProfile(String jwt);
    Seller createSeller(Seller seller);
    Seller getSellerById(Long id);
    Seller getSellerByEmail(String email) throws Exception;
    List<Seller> getAllSeller(AccountStatus status);
    Seller updateSeller(Long id, Seller seller);
    void deleteSeller(Long id);
    Seller verifyEmail(String email,String otp);
    Seller updateSellerAccountStatus(Long sellerId,AccountStatus status);
}
