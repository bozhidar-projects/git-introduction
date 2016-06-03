package com.spaghettisoft.freshhealthfood.interfaces;

import java.util.Map;

public interface Offer {

	int getQuantity();

	Farmer getFarmer();

	void setFarmer(Farmer farmer);

	Map<Product, Integer> getProducts();

	void setProducts(Map<Product, Integer> products);

}
