package com.javafullstackEcommerce.ecommerce.serviceImplimentation;

import com.javafullstackEcommerce.ecommerce.Exception.SellerException;
import com.javafullstackEcommerce.ecommerce.config.JwtProvider;
import com.javafullstackEcommerce.ecommerce.modal.Seller;
import com.javafullstackEcommerce.ecommerce.domain.AccountStatus;
import com.javafullstackEcommerce.ecommerce.domain.USER_ROLE;
import com.javafullstackEcommerce.ecommerce.modal.Address;
import com.javafullstackEcommerce.ecommerce.repository.AddressRepository;
import com.javafullstackEcommerce.ecommerce.repository.SellerRepository;
import com.javafullstackEcommerce.ecommerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private  final AddressRepository addressRepository;


    @Override
    public Seller getSellerProfile(String jwt) throws Exception {
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        return this.getSellerByEmail(email);
    }

    @Override
    public Seller createSeller(Seller seller) throws Exception {
        Seller isExist=sellerRepository.findByEmail(seller.getEmail());
        if(isExist!=null){
            throw  new Exception(" seller already exist ,use different email");
        }

        Address saveAddress=addressRepository.save(seller.getPickupAddress());
        Seller newSeller=new Seller();
        newSeller.setEmail(seller.getEmail());
        newSeller.setPassword(seller.getPassword());
        newSeller.setSellerName(seller.getSellerName());
        newSeller.setPickupAddress(saveAddress);
        newSeller.setGSTIN(seller.getGSTIN());
        newSeller.setRole(USER_ROLE.ROLE_SELLER);
        newSeller.setMobile(seller.getMobile());
        newSeller.setBankDetails(seller.getBankDetails());
        newSeller.setBussinessDetails(seller.getBussinessDetails());
        return sellerRepository.save(newSeller);
    }

    @Override
    public Seller getSellerById(Long id) throws SellerException {

        return sellerRepository.findById(id).
                orElseThrow(()-> new SellerException("seller not found with id "+id));
    }

    @Override
    public Seller getSellerByEmail(String email) throws Exception {
        Seller seller=sellerRepository.findByEmail(email);

        if(email==null){
            throw  new Exception("seller not found............");
        }
        return seller;
    }

    @Override
    public List<Seller> getAllSeller(AccountStatus status) {

        return sellerRepository.findByAccountStatus(status);
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws Exception {
        Seller existingSeller=this.getSellerById(id);

        if(seller.getSellerName()!=null){
            existingSeller.setSellerName(seller.getSellerName());
        }
        if(seller.getMobile()!=null){
            existingSeller.setMobile(seller.getMobile());
        }
        if(seller.getEmail()!=null){
            existingSeller.setEmail(seller.getEmail());
        }

        if(seller.getBussinessDetails()!=null && seller.getBussinessDetails().getBusinessName()!=null){
            existingSeller.getBussinessDetails().setBusinessName(seller.getBussinessDetails().getBusinessName());
        }

        if(seller.getBankDetails()!=null && seller.getBankDetails().getAccountHolderName()!=null
        && seller.getBankDetails().getIfscCode()!=null
        && seller.getBankDetails().getAccountNumber()!=null){
            existingSeller.getBankDetails().setAccountHolderName(seller.getBankDetails().getAccountHolderName());
            existingSeller.getBankDetails().setIfscCode(seller.getBankDetails().getIfscCode());
            existingSeller.getBankDetails().setAccountNumber(seller.getBankDetails().getAccountNumber());
        }

        if(seller.getPickupAddress()!=null
        && seller.getPickupAddress().getAddress()!=null
        && seller.getPickupAddress().getMobileNumber()!=null
        && seller.getPickupAddress().getCity()!=null
        && seller.getPickupAddress().getState()!=null){
            existingSeller.getPickupAddress().setAddress(seller.
                    getPickupAddress().getAddress());
            existingSeller.getPickupAddress().setCity(seller.getPickupAddress()
                    .getCity());
            existingSeller.getPickupAddress().
                    setState(seller.getPickupAddress().getState());
            existingSeller.getPickupAddress().
                    setMobileNumber(seller.getPickupAddress().getMobileNumber());
            existingSeller.getPickupAddress().
                    setPinCode(seller.getPickupAddress().getPinCode());
        }

        if(seller.getGSTIN()!=null){
            existingSeller.setGSTIN(seller.getGSTIN());
        }
        return sellerRepository.save(existingSeller);
    }

    @Override
    public void deleteSeller(Long id) throws Exception {
        Seller seller=this.getSellerById(id);
        sellerRepository.delete(seller);

    }

    @Override
    public Seller verifyEmail(String email, String otp) throws Exception {
        Seller seller=this.getSellerByEmail(email);
        seller.setEmailVerified(true);
        return sellerRepository.save(seller);

    }

    @Override
    public Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws Exception {
        Seller seller=getSellerById(sellerId);
        seller.setAccountStatus(status);
        return sellerRepository.save(seller);

    }
}
