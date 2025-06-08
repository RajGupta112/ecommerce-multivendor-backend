package com.javafullstackEcommerce.ecommerce.serviceImplimentation;

import com.javafullstackEcommerce.ecommerce.controller.Seller;
import com.javafullstackEcommerce.ecommerce.domain.USER_ROLE;
import com.javafullstackEcommerce.ecommerce.modal.User;
import com.javafullstackEcommerce.ecommerce.repository.SellerRepository;
import com.javafullstackEcommerce.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class CustomUserServiceImplimentation implements UserDetailsService {

    private final UserRepository userRepository;
    private static  final String SELLER_PREFIX="seller_";
    private final SellerRepository sellerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.startsWith(SELLER_PREFIX)){
            String actualUserName=username.substring(SELLER_PREFIX.length()
            );
            Seller seller=sellerRepository.findByEmail(actualUserName);
            if(seller!=null){
                return buildUserDeatils(seller.getEmail(),seller.getPassword(),seller.getRole());
            }
        }else {
            User user=userRepository.findByEmail(username);
            if(user!=null){
                return buildUserDeatils(user.getEmail(),user.getPassword(),user.getRole());
            }
        }
        throw  new UsernameNotFoundException("user or seller not found this email"+username);
    }

    private UserDetails buildUserDeatils(String email, String password, USER_ROLE role) {
        if (role==null)role=USER_ROLE.ROLE_CUSTOMER;
        List<GrantedAuthority> authorityList=new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("role"+role));
        return new org.springframework.security.core.userdetails.User(email,password,authorityList);
    }
}
