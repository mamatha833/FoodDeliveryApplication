package com.tap.model;

public class Restaurant {
	
	private int restaurantId;
	private String name;
	private String addresss;
	private String phone;
	private float rating;
	private String cusineType;
	private boolean isActive;
	private String eta;
	private int adminUserId;
	private String imagePath;
	
	public Restaurant() {
		
	}

	public Restaurant(int restaurantId, String name, String addresss, String phone, float rating, String cusineType,
			boolean isActive, String eta, int adminUserId, String imagePath) {
		
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.addresss = addresss;
		this.phone = phone;
		this.rating = rating;
		this.cusineType = cusineType;
		this.isActive = isActive;
		this.eta = eta;
		this.adminUserId = adminUserId;
		this.imagePath = imagePath;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddresss() {
		return addresss;
	}

	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getCusineType() {
		return cusineType;
	}

	public void setCusineType(String cusineType) {
		this.cusineType = cusineType;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Override
	public String toString() {
		
		return restaurantId+" "+name+" "+addresss+" "+phone+" "+rating+" "+cusineType+" "+isActive+" "+eta+" "+adminUserId+" "+imagePath;
	}
}
