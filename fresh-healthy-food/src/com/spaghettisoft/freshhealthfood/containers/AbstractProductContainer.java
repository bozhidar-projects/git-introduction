package com.spaghettisoft.freshhealthfood.containers;

import com.spaghettisoft.freshhealthfood.interfaces.Product;
import com.spaghettisoft.freshhealthfood.interfaces.ProductContainer;

import java.util.Map;

public abstract class AbstractProductContainer implements ProductContainer {
    private int capacity;
    private Map<Product, Integer> products;

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void loadProducts(Map<Product, Integer> products) {
        for (Map.Entry<Product, Integer> productEntry : products.entrySet()) {
            Product product = productEntry.getKey();

            Integer oldProductQuantity = this.products.get(product);
            if (oldProductQuantity == null) {
                oldProductQuantity = 0;
            }
            Integer newProductQuantity = productEntry.getValue();
            this.products.put(product, newProductQuantity + oldProductQuantity);
        }
    }

    @Override
    public void unloadProducts(Map<Product, Integer> products) throws Exception {
        for (Map.Entry<Product, Integer> productEntry : products.entrySet()) {
            Product product = productEntry.getKey();

            Integer oldProductQuantity = this.products.get(product);
            if (oldProductQuantity == null) {
                oldProductQuantity = 0;
            }
            Integer newProductQuantity = productEntry.getValue();
            if (newProductQuantity > oldProductQuantity) {
                throw new Exception("Not enough products");
            }
            this.products.put(product, oldProductQuantity - newProductQuantity);
        }
    }
}
