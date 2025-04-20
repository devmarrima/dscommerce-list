package com.devmarrima.dscommerce_list.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.devmarrima.dscommerce_list.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
