package com.tap.model;

public class CartItem {

	private int menuId;
	private int restaurantId;
	private String itemName;
	private String imagePath;
	private float price;
	private int quantity;
	
	public CartItem() {
		// TODO Auto-generated constructor stub
	}

	public CartItem(int menuId, int restaurantId, String itemName,String imagePath, float price, int quantity) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.imagePath = imagePath;
		this.price = price;
		this.quantity = quantity;
	}

	public int getMenuId() {
		return menuId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public String getItemName() {
		return itemName;
	}
	
	

	public String getImagePath() {
		return imagePath;
	}

	
	public float getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public void setItemName(String ItemName) {
		this.itemName = itemName;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public void setPrice(float price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "[ "+menuId+", "+restaurantId+","+itemName+", "+price+","+quantity+" ]";
	}
	
	
}
