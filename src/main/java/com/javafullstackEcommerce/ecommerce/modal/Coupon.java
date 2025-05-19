package com.javafullstackEcommerce.ecommerce.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private  double discountPercentage;

    private LocalDate validityStartDate;

    private  LocalDate validityEndDate;

    private double minimumOrderValue;

    private boolean isActive=true;

    @ManyToMany(mappedBy = "usedCoupons")
    private List<User> usedByUsers= new ArrayList<>();
}
