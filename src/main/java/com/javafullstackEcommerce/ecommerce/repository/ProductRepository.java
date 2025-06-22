package com.javafullstackEcommerce.ecommerce.repository;

import com.javafullstackEcommerce.ecommerce.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

List<Product> findBySellerId(Long id);


}
