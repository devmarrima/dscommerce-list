package com.devmarrima.dscommerce_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmarrima.dscommerce_list.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
