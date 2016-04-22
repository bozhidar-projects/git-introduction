package com.spaghettisoft.freshhealthfood.interfaces;

public interface Product {
	
	void setType(String type);
	String getType();
	
	void setName(String name);
	String getName();
	
	void setPrice(double price);
	double getPrice();
	
	boolean isAvailable();
	void setAvailability(boolean avbl);
	
	void rate(int stars);
	int getRaiting();
	
	void setFarmer(Farmer farmer);
	Farmer getFarmer();
}
