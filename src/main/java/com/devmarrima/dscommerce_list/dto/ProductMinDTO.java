package com.devmarrima.dscommerce_list.dto;

import com.devmarrima.dscommerce_list.entities.Product;

public class ProductMinDTO {

	private Long id;
	private String name;
	private Double price;
	private String imgUrl;
	private String description;

	public ProductMinDTO() {

	}

	public ProductMinDTO(Long id, String name,String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public ProductMinDTO(Product entity) {
		id = entity.getId();
		name = entity.getName();
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();
		description = entity.getDescription();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public String getDescription() {
		return description;
	}

	
}
