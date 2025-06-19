package com.javafullstackEcommerce.ecommerce.controller;

import com.javafullstackEcommerce.ecommerce.domain.USER_ROLE;
import com.javafullstackEcommerce.ecommerce.modal.User;
import com.javafullstackEcommerce.ecommerce.modal.VerificationCode;
import com.javafullstackEcommerce.ecommerce.repository.UserRepository;
import com.javafullstackEcommerce.ecommerce.response.*;
import com.javafullstackEcommerce.ecommerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {

        String jwt= authService.createUser(req);

        AuthResponse authResponse= new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("register success");
        authResponse.setRole(USER_ROLE.ROLE_CUSTOMER);

        return  ResponseEntity.ok(authResponse);
    }


    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {
        authService.sendLoginOtp(req.getEmail(),req.getRole());

        ApiResponse res= new ApiResponse();

        res.setMessage("otp sent successfully");
        return ResponseEntity.ok((res));
    }


    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception{
      AuthResponse authResponse=  authService.signing(req);
      return ResponseEntity.ok(authResponse);



    }

}
