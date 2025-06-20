package com.javafullstackEcommerce.ecommerce.modal;

import com.javafullstackEcommerce.ecommerce.domain.OrderStatus;
import com.javafullstackEcommerce.ecommerce.domain.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;

    @ManyToOne
    private  User  user;

    private Long sellerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItem= new ArrayList<>();

    @ManyToOne
    private  Address shippingAddress;

    @Embedded
    private PaymentDetails paymentDetails= new PaymentDetails();

    private double totalMrpPrice;

    private Integer totalSellingPrice;

    private Integer discount;

    private OrderStatus orderStatus;

    private int totalItem;

    private PaymentStatus paymentStatus=PaymentStatus.PENDING;

    private LocalDateTime orderedDate= LocalDateTime.now();

    private LocalDateTime deliverDate= orderedDate.plusDays(
            7
    );






}
