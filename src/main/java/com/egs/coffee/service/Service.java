package com.egs.coffee.service;

import com.egs.coffee.model.Product;

public interface Service {
    /**
     * @param product
     */
    void add(Product product);

    /**
     * This method getting product by name and returning Product;
     * @param name
     * @return
     */
    Product getByName(String name);
}
