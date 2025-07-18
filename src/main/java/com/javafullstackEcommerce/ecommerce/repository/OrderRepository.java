package com.javafullstackEcommerce.ecommerce.repository;

import com.javafullstackEcommerce.ecommerce.modal.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public  interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUserId(Long userId);
    List<Order> findBySellerId(Long sellerId);
}
