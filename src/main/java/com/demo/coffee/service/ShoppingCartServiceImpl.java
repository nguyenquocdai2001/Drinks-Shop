package com.demo.coffee.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.demo.coffee.entity.CartItem;
import com.demo.coffee.entity.Product;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	Map<Integer, CartItem> maps = new HashMap<>(); //gio hang
	@Override
	public void add(CartItem item) {
		CartItem cartItem = maps.get(item.getProductID());
		if(cartItem == null) {
			maps.put(item.getProductID(), item);
		}else {
			cartItem.setQty(cartItem.getQty() + 1);
		}
	}
	
	@Override
	public void remove(int id) {
		maps.remove(id);
	}
	
	@Override
	public CartItem update(int proID, int qty) {
		CartItem cartItem = maps.get(proID);
		cartItem.setQty(qty);
		
		return cartItem;
	}
	
	@Override
	public void clear() {
		maps.clear();
	}
	
	@Override
	public Collection<CartItem> getAllItems(){
		return maps.values();
	}
	
	@Override
	public int getCount() {
		return maps.values().size();
	}
	
	@Override
	public double getAmount() {
		return maps.values().stream().mapToDouble(item -> item.getQty() * item.getPrice()).sum();
	}
}
