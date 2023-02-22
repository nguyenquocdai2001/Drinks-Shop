package com.demo.coffee.service;

import java.util.List;

import com.demo.coffee.entity.Product;

public interface ProductService {
	public int add(Product p);
	public int remove(int id);
	public List<Product> getAll();
}
