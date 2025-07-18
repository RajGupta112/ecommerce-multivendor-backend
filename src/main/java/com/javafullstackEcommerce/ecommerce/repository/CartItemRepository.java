package com.javafullstackEcommerce.ecommerce.repository;

import com.javafullstackEcommerce.ecommerce.modal.Cart;
import com.javafullstackEcommerce.ecommerce.modal.CartItem;
import com.javafullstackEcommerce.ecommerce.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
