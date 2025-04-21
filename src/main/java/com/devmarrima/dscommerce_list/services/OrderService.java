package com.devmarrima.dscommerce_list.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmarrima.dscommerce_list.dto.OrderDTO;
import com.devmarrima.dscommerce_list.dto.OrderItemDTO;
import com.devmarrima.dscommerce_list.entities.Order;
import com.devmarrima.dscommerce_list.entities.OrderItem;
import com.devmarrima.dscommerce_list.entities.OrderStatus;
import com.devmarrima.dscommerce_list.entities.Product;
import com.devmarrima.dscommerce_list.entities.User;
import com.devmarrima.dscommerce_list.repositories.OrderItemRepository;
import com.devmarrima.dscommerce_list.repositories.OrderRepository;
import com.devmarrima.dscommerce_list.repositories.ProductRepository;
import com.devmarrima.dscommerce_list.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private AuthService authService;

	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		authService.validateSelfOrAdmin(order.getClient().getId());		
		return new OrderDTO(order);
	}

	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order();

		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITNG_PAYMENT);

		User user = userService.authenticated();
		order.setClient(user);

		for(OrderItemDTO itemDto : dto.getItems()){
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			OrderItem orderItem = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
			 order.getItems().add(orderItem);
		}
		repository.save(order);
		orderItemRepository.saveAll(order.getItems());
		return new OrderDTO(order);
	}

}
