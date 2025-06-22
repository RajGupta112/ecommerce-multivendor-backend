package com.javafullstackEcommerce.ecommerce.repository;

import com.javafullstackEcommerce.ecommerce.modal.Category;
import com.sun.jdi.event.StepEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository     extends JpaRepository<Category,Long> {


    Category findByCategory(String categoryId);
}
