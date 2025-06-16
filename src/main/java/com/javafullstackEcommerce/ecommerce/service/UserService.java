package com.javafullstackEcommerce.ecommerce.service;

import com.javafullstackEcommerce.ecommerce.modal.User;

public interface UserService {
    User findUserByJwtToken(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
}
