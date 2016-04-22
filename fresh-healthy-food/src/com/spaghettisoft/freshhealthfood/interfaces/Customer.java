package com.spaghettisoft.freshhealthfood.interfaces;

public interface Customer {
	
	void setAddress(String address);
	String getAddress();
	
	void setName(String name);
	String getName();
	
	void setPhone(String phone);
	String getPhone();
	
	void registerInSite();
	void placeOrder(Product product);
	void receiveProducts(Product product);
}
