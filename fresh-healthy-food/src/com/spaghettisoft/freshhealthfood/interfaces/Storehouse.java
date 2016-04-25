package com.spaghettisoft.freshhealthfood.interfaces;

import java.util.List;

import com.spaghettisoft.freshhealthfood.interfaces.Product;

public interface Storehouse extends ProductContainer {

	void setCapacity(int capacity);

	int getCapacity();

	void setAdress(String adress);

	String getAdress();

	void setProducts(List<Product> products);

	List<Product> getProducts();

	boolean isFull();
}
