package com.spaghettisoft.freshhealthfood.interfaces;

import java.util.Map;

public interface ProductContainer {
    int getCapacity();

    boolean isFull();

    void loadProducts(Map<Product, Integer> products);

    void unloadProducts(Map<Product, Integer> products) throws Exception;
}
