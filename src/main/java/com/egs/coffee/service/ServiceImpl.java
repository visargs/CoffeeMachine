package com.egs.coffee.service;

import com.egs.coffee.model.Product;
import com.egs.coffee.utils.SerializeUtil;

import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements Service {
    private List<Product> products = new ArrayList<>();

    public void add(Product product) {
        products.add(product);
        SerializeUtil.serializeProduct(products);
    }

    public Product getByName(String name) {
        boolean isFind = false;
        for (Product product : products) {
            if (product.getName().equals(name)) {
                isFind = true;
                return product;
            }
            if (isFind) {
                System.out.println("Product does not exist");
            }
        }
        return null;
    }

    /**
     * Ths method is deserialize products form file and checking the null process.
     */
    public void init() {
        List<Product> productsFromFile = SerializeUtil.deserializeProduct();
        if (productsFromFile != null) {
            products = productsFromFile;
        }
    }
}

