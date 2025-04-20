package com.devmarrima.dscommerce_list.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmarrima.dscommerce_list.dto.OrderDTO;
import com.devmarrima.dscommerce_list.entities.Order;
import com.devmarrima.dscommerce_list.repositories.OrderRepository;
import com.devmarrima.dscommerce_list.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new OrderDTO(order);
	}

}
