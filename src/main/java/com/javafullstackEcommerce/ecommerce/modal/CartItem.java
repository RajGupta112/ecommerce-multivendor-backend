package com.javafullstackEcommerce.ecommerce.modal;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Cart cart;

    private  Product product;

    private String size;

    private int quantity=1;

    private  Integer mrpPrice;

    private Integer sellingPrice;

    private Long UserId;


}
