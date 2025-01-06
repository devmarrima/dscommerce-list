package com.devmarrima.dscommerce_list.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmarrima.dscommerce_list.dto.ProductDTO;
import com.devmarrima.dscommerce_list.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value = ("/{id}"))
	public ProductDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}

}
