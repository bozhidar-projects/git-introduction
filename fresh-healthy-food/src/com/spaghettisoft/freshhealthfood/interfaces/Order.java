package com.spaghettisoft.freshhealthfood.interfaces;

import com.sun.javafx.collections.MappingChange.Map;

public interface Order {

    int getTotalPrice();

    boolean orderIsAccepted();

    Map<Product, Integer> getQuantity();

    void setQuantity();

    Customer getCustomer();

    void setCustomer(Customer customer);

    int getTotalPrice(Customer customer, Map<Product, Integer> products);

}
