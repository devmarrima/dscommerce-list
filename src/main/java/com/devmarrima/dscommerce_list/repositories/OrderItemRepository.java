package com.devmarrima.dscommerce_list.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.devmarrima.dscommerce_list.entities.OrderItem;
import com.devmarrima.dscommerce_list.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
