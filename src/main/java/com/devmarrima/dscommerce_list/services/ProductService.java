package com.devmarrima.dscommerce_list.services;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmarrima.dscommerce_list.dto.ProductDTO;
import com.devmarrima.dscommerce_list.entities.Product;
import com.devmarrima.dscommerce_list.repositories.ProductRepository;

@ Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product product = repository.findById(id).get();
		return new ProductDTO(product);
	}

}
