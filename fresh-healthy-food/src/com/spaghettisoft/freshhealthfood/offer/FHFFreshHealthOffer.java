package com.spaghettisoft.freshhelthfood.offers;

import java.util.Map;

import com.spaghettisoft.freshhealthfood.interfaces.Farmer;
import com.spaghettisoft.freshhealthfood.interfaces.Offer;
import com.spaghettisoft.freshhealthfood.interfaces.Product;

public class FHFFreshHealthOffer implements Offer {

	private int quantity;
	private Farmer farmer;
	private Map<Product, Integer> products;

	public FHFFreshHealthOffer(int quantity, Farmer farmer, Map<Product, Integer> products) {
		super();
		this.quantity = quantity;
		this.farmer = farmer;
		this.products = products;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public Farmer getFarmer() {
		return farmer;
	}

	@Override
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	@Override
	public Map<Product, Integer> getProducts() {

		for (Map.Entry<Product, Integer> productEntry : products.entrySet()) {

		}

		return products;
	}

	@Override
	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}

}
