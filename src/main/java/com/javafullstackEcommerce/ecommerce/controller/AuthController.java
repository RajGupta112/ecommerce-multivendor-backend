package com.javafullstackEcommerce.ecommerce.controller;

import com.javafullstackEcommerce.ecommerce.domain.USER_ROLE;
import com.javafullstackEcommerce.ecommerce.modal.User;
import com.javafullstackEcommerce.ecommerce.repository.UserRepository;
import com.javafullstackEcommerce.ecommerce.response.AuthResponse;
import com.javafullstackEcommerce.ecommerce.response.SignupRequest;
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

    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req){

        String jwt= authService.createUser(req);

        AuthResponse authResponse= new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("register success");
        authResponse.setRole(USER_ROLE.ROLE_CUSTOMER);

        return  ResponseEntity.ok(authResponse);
    }
}
