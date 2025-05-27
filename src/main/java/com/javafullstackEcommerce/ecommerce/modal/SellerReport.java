package com.javafullstackEcommerce.ecommerce.modal;


import com.javafullstackEcommerce.ecommerce.controller.Seller;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SellerReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Seller seller;

    private  Long totalEarning=0L;

    private Long totalSales=0L;


    private Long totalRefunds=0L;

    private Long totalTax=0L;

    private Long netEarning=0L;

    private  Integer totalOrders=0;

    private  Integer cancelOrders=0;

    private Integer totalTransactions=0;

}
