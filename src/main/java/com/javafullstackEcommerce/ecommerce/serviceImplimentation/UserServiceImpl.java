package com.javafullstackEcommerce.ecommerce.serviceImplimentation;

import com.javafullstackEcommerce.ecommerce.config.JwtProvider;
import com.javafullstackEcommerce.ecommerce.modal.User;
import com.javafullstackEcommerce.ecommerce.repository.UserRepository;
import com.javafullstackEcommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        User user=this.findUserByEmail(email);

        return this.findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("user not found with email- "+email);
        }
        return user;
    }
}
