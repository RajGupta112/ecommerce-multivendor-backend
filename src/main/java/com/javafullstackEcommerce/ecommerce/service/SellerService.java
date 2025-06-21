package com.javafullstackEcommerce.ecommerce.service;

import com.javafullstackEcommerce.ecommerce.Exception.SellerException;
import com.javafullstackEcommerce.ecommerce.modal.Seller;
import com.javafullstackEcommerce.ecommerce.domain.AccountStatus;

import java.util.List;

public interface SellerService {
    Seller getSellerProfile(String jwt) throws Exception;
    Seller createSeller(Seller seller) throws Exception;
    Seller getSellerById(Long id) throws SellerException;
    Seller getSellerByEmail(String email) throws Exception;
    List<Seller> getAllSeller(AccountStatus status);
    Seller updateSeller(Long id, Seller seller) throws Exception;
    void deleteSeller(Long id) throws Exception;
    Seller verifyEmail(String email,String otp) throws Exception;
    Seller updateSellerAccountStatus(Long sellerId,AccountStatus status) throws Exception;
}
