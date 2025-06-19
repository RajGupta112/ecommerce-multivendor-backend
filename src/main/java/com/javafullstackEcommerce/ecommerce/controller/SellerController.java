package com.javafullstackEcommerce.ecommerce.controller;

import com.javafullstackEcommerce.ecommerce.config.JwtProvider;
import com.javafullstackEcommerce.ecommerce.domain.AccountStatus;
import com.javafullstackEcommerce.ecommerce.modal.Seller;
import com.javafullstackEcommerce.ecommerce.modal.VerificationCode;
import com.javafullstackEcommerce.ecommerce.repository.VerificationCodeRepository;
import com.javafullstackEcommerce.ecommerce.response.ApiResponse;
import com.javafullstackEcommerce.ecommerce.response.AuthResponse;
import com.javafullstackEcommerce.ecommerce.response.LoginRequest;
import com.javafullstackEcommerce.ecommerce.service.AuthService;
import com.javafullstackEcommerce.ecommerce.service.SellerService;
import com.javafullstackEcommerce.ecommerce.serviceImplimentation.EmailService;
import com.javafullstackEcommerce.ecommerce.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PrivilegedAction;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthService authService;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;




    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(@RequestBody LoginRequest req) throws  Exception{
        String otp=req.getOtp();
        String email=req.getEmail();

        req.setEmail("seller_"+email);
        AuthResponse authResponse=authService.signing(req);
        return ResponseEntity.ok(authResponse);
    }


    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception {
     VerificationCode verificationCode=verificationCodeRepository.findByOtp(otp);

     if(verificationCode==null || !verificationCode.getOtp().equals(otp)){
         throw  new Exception("wrong otp");
     }

     Seller seller=sellerService.verifyEmail(verificationCode.getEmail(),otp);
 return new ResponseEntity<>(seller, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception{
        Seller savedSeller=sellerService.createSeller(seller);

        String otp= OtpUtil.generateOtp();
        VerificationCode verificationCode= new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(seller.getEmail());
        verificationCodeRepository.save(verificationCode);

        String subject = " OffMint Email Verification Code";
        String text = "Welcome to OffMint, verify your account using this link ";
        String frontend_url = "http://localhost:3000/verify-seller/";
         emailService.sendVerificationOtpEmail(seller.getEmail(), verificationCode.getOtp(), subject,text+frontend_url);
         return new ResponseEntity<>(savedSeller,HttpStatus.CREATED);
    }


@GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws Exception {
        Seller seller=sellerService.getSellerById(id);
        return new ResponseEntity<>(seller,HttpStatus.OK);

    }
    @GetMapping("/profile")
public ResponseEntity<Seller> getSellerByJwt(@RequestHeader("Authorization") String jwt) throws Exception {

        Seller seller=sellerService.getSellerProfile(jwt);
        return new ResponseEntity<>(seller,HttpStatus.OK);
}

@GetMapping
public ResponseEntity<List<Seller>> getAllSeller(@RequestParam(required = false)AccountStatus status){
        List<Seller> sellers=sellerService.getAllSeller(status);
        return ResponseEntity.ok(sellers);
}

@PatchMapping
public ResponseEntity<Seller> updateSeller(@RequestHeader("Authorization") String jwt,Seller seller) throws Exception {
        Seller profile=sellerService.getSellerProfile(jwt);
        Seller update=sellerService.updateSeller(profile.getId(),seller);
        return ResponseEntity.ok(update);
}

@DeleteMapping
public ResponseEntity<Seller> deleteSeller(@PathVariable Long id) throws Exception {
      sellerService.deleteSeller(id);
      return ResponseEntity.noContent().build();
}
}
