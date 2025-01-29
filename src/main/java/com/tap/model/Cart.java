package com.tap.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {

	Map<Integer,CartItem> cart;
	

	public Cart() {
		cart = new HashMap<Integer, CartItem>();
	}
	
	

	public void addCartItem(CartItem newItem){

		if(cart.containsKey(newItem.getMenuId())) {

			CartItem existingItem = cart.get(newItem.getMenuId());
			existingItem.setQuantity(existingItem.getQuantity() + newItem.getQuantity());
		}
		else {
			cart.put(newItem.getMenuId(), newItem);
		}

	}
	
	
	

	public void removeCartItem(int menuId) {
		cart.remove(menuId);
	}

	
	public void clear() {
		cart.clear();
	}
	
	
	public Map<Integer,CartItem> getItems(){
		return cart;
	}
	
	
	public void updateCartItem(int menuId, int quantity) {
		
		if(cart.containsKey(menuId)) {
			if(quantity<=0) {
				cart.remove(menuId);
			}
			else {
				cart.get(menuId).setQuantity(quantity);
			}
		}
	}
	
	public float getSubTotalPrice() {
		int totalPrice = 0;
		Collection<CartItem> cartItems = cart.values();
		for(CartItem cartItem : cartItems) {
			totalPrice += cartItem.getPrice()*cartItem.getQuantity();
		}
		return totalPrice;
	}


}
