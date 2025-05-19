package com.javafullstackEcommerce.ecommerce.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javafullstackEcommerce.ecommerce.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String FullName;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  String  password;

    private String mobile;

    private USER_ROLE role= USER_ROLE.ROLE_CUSTOMER;

    @OneToMany
    private Set<Address> addresses= new HashSet<>();

    @ManyToMany
    @JsonIgnore
    private Set<Coupon> usedCoupons= new HashSet<>();

}
