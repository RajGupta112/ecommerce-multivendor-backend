package com.javafullstackEcommerce.ecommerce.repository;

import com.javafullstackEcommerce.ecommerce.modal.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUserId(Long id);
}
