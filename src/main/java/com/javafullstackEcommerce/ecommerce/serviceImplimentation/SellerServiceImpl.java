package com.javafullstackEcommerce.ecommerce.serviceImplimentation;

import com.javafullstackEcommerce.ecommerce.config.JwtProvider;
import com.javafullstackEcommerce.ecommerce.controller.Seller;
import com.javafullstackEcommerce.ecommerce.domain.AccountStatus;
import com.javafullstackEcommerce.ecommerce.repository.SellerRepository;
import com.javafullstackEcommerce.ecommerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final JwtProvider jwtProvider;


    @Override
    public Seller getSellerProfile(String jwt) {
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        return this.getSellerByEmail(email);
    }

    @Override
    public Seller createSeller(Seller seller) {
        return null;
    }

    @Override
    public Seller getSellerById(Long id) {
        return null;
    }

    @Override
    public Seller getSellerByEmail(String email) throws Exception {
        Seller seller=sellerRepository.findByEmail(email);

        if(email==null){
            throw  new Exception("seller not found............")
        }
        return seller;
    }

    @Override
    public List<Seller> getAllSeller(AccountStatus status) {
        return List.of();
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) {
        return null;
    }

    @Override
    public void deleteSeller(Long id) {

    }

    @Override
    public Seller verifyEmail(String email, String otp) {
        return null;
    }

    @Override
    public Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) {
        return null;
    }
}
