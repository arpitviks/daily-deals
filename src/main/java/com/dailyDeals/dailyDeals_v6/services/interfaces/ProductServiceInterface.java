package com.dailyDeals.dailyDeals_v6.services.interfaces;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.Product;

import java.util.List;

public interface ProductServiceInterface {
    Product addProduct(Product product, String userName) throws CustomGlobalException;

    List<Product> getAllProducts() throws CustomGlobalException;

    Product getProduct(int productId) throws CustomGlobalException;

    Object deleteProduct(int productId, String userName) throws CustomGlobalException;

    Product updateProduct(Product product, String userName) throws CustomGlobalException;
}
