package com.javafullstackEcommerce.ecommerce.serviceImplimentation;

import com.javafullstackEcommerce.ecommerce.config.JwtProvider;
import com.javafullstackEcommerce.ecommerce.domain.USER_ROLE;
import com.javafullstackEcommerce.ecommerce.modal.Cart;
import com.javafullstackEcommerce.ecommerce.modal.Seller;
import com.javafullstackEcommerce.ecommerce.modal.User;
import com.javafullstackEcommerce.ecommerce.modal.VerificationCode;
import com.javafullstackEcommerce.ecommerce.repository.CartRepository;
import com.javafullstackEcommerce.ecommerce.repository.SellerRepository;
import com.javafullstackEcommerce.ecommerce.repository.UserRepository;
import com.javafullstackEcommerce.ecommerce.repository.VerificationCodeRepository;
import com.javafullstackEcommerce.ecommerce.response.AuthResponse;
import com.javafullstackEcommerce.ecommerce.response.LoginRequest;
import com.javafullstackEcommerce.ecommerce.response.SignupRequest;
import com.javafullstackEcommerce.ecommerce.service.AuthService;
import com.javafullstackEcommerce.ecommerce.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImplimentation  implements AuthService {

    private  final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    private final CartRepository cartRepository;

    private  final JwtProvider jwtProvider;

    private final VerificationCodeRepository verificationCodeRepository;

    private  final EmailService emailService;

    private final CustomUserServiceImplimentation customUserService;

    private final SellerRepository sellerRepository;



    @Override
    public void sendLoginOtp(String email,USER_ROLE role) throws Exception {
 String SIGNING_PREFIX="signing_";
// String SELLER_PREFIX="seller_";
 if(email.startsWith(SIGNING_PREFIX)){
     email=email.substring(SIGNING_PREFIX.length());

     if(role.equals(USER_ROLE.ROLE_SELLER)) {
         Seller seller=sellerRepository.findByEmail(email);
         if(seller==null){
             throw new Exception("seller not found");
         }


     }else{
         User user = userRepository.findByEmail(email);
         if (user == null) {
             throw new Exception("user not exist with provided email");
         }
     }
 }
 VerificationCode isExist=verificationCodeRepository.findByEmail(email);

 if(isExist!=null){
     verificationCodeRepository.delete(isExist);
 }
 String otp= OtpUtil.generateOtp();
 VerificationCode verificationCode= new VerificationCode();
 verificationCode.setOtp(otp);
 verificationCode.setEmail(email);
 verificationCodeRepository.save(verificationCode);

 String subject="Raj_Ecommerce login/signup otp";
 String text="your login/signup otp is"+otp;

 emailService.sendVerificationOtpEmail(email,otp,subject,text
 );

    }

    @Override
    public String createUser(SignupRequest req) throws Exception {

        VerificationCode verificationCode=verificationCodeRepository.findByEmail(req.getEmail());

        if(verificationCode==null || !verificationCode.getOtp().equals(req.getOtp())){
            throw new Exception("wrong otp....");
        }
        User user= userRepository.findByEmail(req.getEmail());

        if(user==null) {
            User createdUser = new User();
            createdUser.setEmail(req.getEmail());
            createdUser.setFullName(req.getFullName());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createdUser.setMobile("8699161980");
            createdUser.setPassword(passwordEncoder.encode(req.getOtp()));

            user = userRepository.save(createdUser);
            Cart cart= new Cart();
            cart.setUser(user);
            cartRepository.save(cart);

        }
        List<GrantedAuthority> authority= new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication authentication=  new UsernamePasswordAuthenticationToken(req.getEmail(),null,authority);

        SecurityContextHolder.getContext().setAuthentication(authentication);

   return  jwtProvider.generateToken(authentication);
    }

    @Override
    public AuthResponse signing(LoginRequest req) {
        String username=req.getEmail();

        String otp =req.getOtp();

        Authentication authentication=authenticate(username,otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token= jwtProvider.generateToken(authentication);

        AuthResponse authResponse= new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login Success");
        return authResponse;
    }

    private Authentication authenticate(String username, String otp) {
        UserDetails userDetails=customUserService.loadUserByUsername(username);
        if(userDetails==null){
            throw  new BadCredentialsException("invalid username");
        }
        VerificationCode verificationCode=verificationCodeRepository.findByEmail(username);

        if (verificationCode==null || !verificationCode.getOtp().equals(otp
        )){
            throw new BadCredentialsException("wrongOtp");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
