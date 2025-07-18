package com.javafullstackEcommerce.ecommerce.service;

import com.javafullstackEcommerce.ecommerce.domain.OrderStatus;
import com.javafullstackEcommerce.ecommerce.modal.Address;
import com.javafullstackEcommerce.ecommerce.modal.Cart;
import com.javafullstackEcommerce.ecommerce.modal.Order;
import com.javafullstackEcommerce.ecommerce.modal.User;

import java.util.List;
import java.util.Set;

public interface OrderService {

    public Set<Order> createOrder(User user, Address shippingAddress, Cart cart);

    public Order findOrderById(Long orderId) throws Exception;

    public List<Order> usersOrderHistory(Long userId);

    public List<Order>getShopsOrders(Long sellerId);

    public Order updateOrderStatus(Long orderId,
                                   OrderStatus orderStatus)
            throws Exception;



    Order cancelOrder(Long orderId,User user) throws Exception;
}
