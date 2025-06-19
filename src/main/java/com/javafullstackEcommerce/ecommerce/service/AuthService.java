package com.javafullstackEcommerce.ecommerce.service;

import com.javafullstackEcommerce.ecommerce.domain.USER_ROLE;
import com.javafullstackEcommerce.ecommerce.response.AuthResponse;
import com.javafullstackEcommerce.ecommerce.response.LoginRequest;
import com.javafullstackEcommerce.ecommerce.response.SignupRequest;

public interface AuthService {

    void sendLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthResponse signing(LoginRequest req);
}
