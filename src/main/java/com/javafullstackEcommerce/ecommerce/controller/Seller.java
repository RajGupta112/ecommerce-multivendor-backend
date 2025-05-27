package com.javafullstackEcommerce.ecommerce.controller;


import com.javafullstackEcommerce.ecommerce.domain.AccountStatus;
import com.javafullstackEcommerce.ecommerce.domain.USER_ROLE;
import com.javafullstackEcommerce.ecommerce.modal.Address;
import com.javafullstackEcommerce.ecommerce.modal.BankDetails;
import com.javafullstackEcommerce.ecommerce.modal.BussinessDetails;
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
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sellerName;

    private  String mobile;

    @Column(unique = true,nullable = false)
    private String email;
    private  String password;


    @Embedded
    private BussinessDetails bussinessDetails= new BussinessDetails();

       @Embedded
        private BankDetails bankDetails= new BankDetails();

       @OneToOne(cascade = CascadeType.ALL)
       private Address pickupAddress= new Address();

       private String GSTIN;

       private USER_ROLE role=USER_ROLE.ROLE_SELLER;

       private boolean isEmailVerified=false;

       private AccountStatus accountStatus= AccountStatus.PENDING_VERIFICATION;
}
