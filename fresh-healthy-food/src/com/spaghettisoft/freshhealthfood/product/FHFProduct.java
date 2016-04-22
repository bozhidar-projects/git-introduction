package com.spaghettisoft.freshhealthfood.product;

import com.spaghettisoft.freshhealthfood.interfaces.Farmer;
import com.spaghettisoft.freshhealthfood.interfaces.Product;

public class FHFProduct implements Product {

	private String type, name;
	private Farmer farmer;
	private double price;
	private boolean availability;
	private int raterCount;
	private int rating = 0;
	
	public FHFProduct(String type, String name, Farmer farmer, double price) {
		this.type=type;
		this.name=name;
		this.farmer=farmer;
		this.price=price;
	}

	public FHFProduct() {

	}

	@Override
	public void rate(int stars) {
		rating += stars;
		raterCount++;
	}
	
	@Override
	public int getRaiting() {
		return rating/raterCount;
	}

	@Override
	public void setAvailability(boolean avbl) {
		this.availability = avbl;
	}

	@Override
	public boolean isAvailable() {
		return availability;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public double getPrice() {
		return price;
	}

	

	@Override
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	@Override
	public Farmer getFarmer() {
		return farmer;
	}

}
