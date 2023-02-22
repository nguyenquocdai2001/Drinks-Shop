package com.demo.coffee.service;

import java.util.Collection;

import com.demo.coffee.entity.CartItem;

public interface ShoppingCartService {

	double getAmount();

	int getCount();

	Collection<CartItem> getAllItems();

	void clear();

	CartItem update(int proID, int qty);

	void remove(int id);

	void add(CartItem item);
	
}
