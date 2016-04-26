package com.spaghettisoft.freshhealthfood.truck;

import java.util.Map;

import com.spaghettisoft.freshhealthfood.containers.AbstractProductContainer;
import com.spaghettisoft.freshhealthfood.interfaces.Product;
import com.spaghettisoft.freshhealthfood.interfaces.Truck;

public class FHFTruck extends AbstractProductContainer implements Truck {

	private static final int MAX_CAPACITY = 300;
	private int number;

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return MAX_CAPACITY == getCapacity();
	}

	@Override
	public void loadProducts(Map<Product, Integer> products) throws Exception {
		int newCapacity = 0;
        for (Map.Entry<Product, Integer> productEntry : products.entrySet()) {
			newCapacity += productEntry.getValue();
		}
        if( newCapacity + getCapacity() > MAX_CAPACITY ){
        	throw new Exception("The FHFTruck will be overloaded");
        }
		super.loadProducts(products);
		setCapacity(newCapacity);
		
	}

	@Override
	public void unloadProducts(Map<Product, Integer> products) throws Exception {
		// TODO Auto-generated method stub

		int newCapacity = 0;
        for (Map.Entry<Product, Integer> productEntry : products.entrySet()) {
			newCapacity += productEntry.getValue();
		}
        
        if(getCapacity() - newCapacity < 0 ){
        	throw new Exception("Too many unloaded products");
        }
        super.unloadProducts(products);
        

	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return number;
	}

	@Override
	public void setNumber(int number) {
		// TODO Auto-generated method stub
		this.number = number;
	}

	@Override
	public void setCapacity(int capacity) {
		// TODO Auto-generated method stub
		setCapacity(capacity);
	}

	@Override
	public void drive(String from, String to) {
		// TODO Auto-generated method stub
		System.out.println("FHFTruck is driving " + from + " to " + to);
	}

}
