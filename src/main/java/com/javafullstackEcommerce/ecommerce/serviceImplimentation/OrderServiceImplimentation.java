package com.javafullstackEcommerce.ecommerce.serviceImplimentation;

import com.javafullstackEcommerce.ecommerce.domain.OrderStatus;
import com.javafullstackEcommerce.ecommerce.modal.Address;
import com.javafullstackEcommerce.ecommerce.modal.Cart;
import com.javafullstackEcommerce.ecommerce.modal.Order;
import com.javafullstackEcommerce.ecommerce.modal.User;
import com.javafullstackEcommerce.ecommerce.repository.AddressRepository;
import com.javafullstackEcommerce.ecommerce.repository.OrderRepository;
import com.javafullstackEcommerce.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImplimentation implements OrderService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    @Override
    public Set<Order> createOrder(User user, Address shippingAddress, Cart cart) {
        return Set.of();
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        return null;
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return List.of();
    }

    @Override
    public List<Order> getShopsOrders(Long sellerId) {
        return List.of();
    }

    @Override
    public Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception {
        return null;
    }

    @Override
    public Order cancelOrder(Long orderId, User user) throws Exception {
        return null;
    }
}
