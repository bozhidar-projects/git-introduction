package com.spaghettisoft.freshhealthfood.storehouse;

import java.util.List;

import com.spaghettisoft.freshhealthfood.containers.AbstractProductContainer;
import com.spaghettisoft.freshhealthfood.interfaces.Product;
import com.spaghettisoft.freshhealthfood.interfaces.Storehouse;

public class FHFStoreHouse extends AbstractProductContainer implements Storehouse {
	
	private int capacity;
	private String adress;
	private List<Product> products;
	
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String getAdress() {
		return adress;
	}
	
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public boolean isFull(){
		return products.size() == capacity;
	}


	
	
	
	
	

}
