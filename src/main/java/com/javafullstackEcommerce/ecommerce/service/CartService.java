package com.javafullstackEcommerce.ecommerce.service;

import com.javafullstackEcommerce.ecommerce.Exception.ProductException;
import com.javafullstackEcommerce.ecommerce.modal.Cart;
import com.javafullstackEcommerce.ecommerce.modal.CartItem;
import com.javafullstackEcommerce.ecommerce.modal.Product;
import com.javafullstackEcommerce.ecommerce.modal.User;
import org.springframework.stereotype.Repository;

@Repository
public interface CartService {


    public CartItem addCartItem(User user,
                                Product product,
                                String size,
                                int quantity) throws ProductException;

    public Cart findUserCart(User user);

}
