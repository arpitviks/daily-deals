package com.dailyDeals.dailyDeals_v6.services.interfaces;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.ProductEntity;

import java.util.List;

public interface ProductServiceInterface {
    ProductEntity addProduct(ProductEntity product, String userName) throws CustomGlobalException;

    List<ProductEntity> getAllProducts() throws CustomGlobalException;

    ProductEntity getProduct(int productId) throws CustomGlobalException;

    Object deleteProduct(int productId, String userName) throws CustomGlobalException;

    ProductEntity updateProduct(ProductEntity product, String userName) throws CustomGlobalException;
}
