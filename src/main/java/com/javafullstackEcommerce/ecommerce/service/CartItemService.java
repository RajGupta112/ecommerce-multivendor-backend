package com.javafullstackEcommerce.ecommerce.service;

import com.javafullstackEcommerce.ecommerce.modal.CartItem;

public interface CartItemService {
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception;

    public void removeCartItem(Long userId,Long cartItemId) throws Exception;

    public CartItem findCartItemById(Long cartItemId) throws Exception;

}
