package com.javafullstackEcommerce.ecommerce.service;

import com.javafullstackEcommerce.ecommerce.response.SignupRequest;

public interface AuthService {
    String createUser(SignupRequest req);
}
