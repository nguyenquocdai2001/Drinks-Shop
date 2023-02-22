package com.demo.coffee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
	private Integer productID;
	private String name;
	private double price;
	private int qty = 1;
}
