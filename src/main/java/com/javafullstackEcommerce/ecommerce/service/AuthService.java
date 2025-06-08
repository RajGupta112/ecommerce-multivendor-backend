package com.javafullstackEcommerce.ecommerce.service;

import com.javafullstackEcommerce.ecommerce.response.SignupRequest;

public interface AuthService {

    void sendLoginOtp(String email) throws Exception;
    String createUser(SignupRequest req) throws Exception;
}
