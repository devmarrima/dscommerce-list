package com.devmarrima.dscommerce_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmarrima.dscommerce_list.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
