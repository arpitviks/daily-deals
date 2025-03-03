package com.dailyDeals.dailyDeals_v6.services;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.enums.UserRole;
import com.dailyDeals.dailyDeals_v6.models.Product;
import com.dailyDeals.dailyDeals_v6.models.User;
import com.dailyDeals.dailyDeals_v6.repositories.interfaces.ProductRepo;
import com.dailyDeals.dailyDeals_v6.services.interfaces.ProductServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService implements ProductServiceInterface {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    UserService userService;

    @Override
    public Product addProduct(Product product, String userName) throws CustomGlobalException {
        //check user is admin or not with userService utility
        User user = userService.getUser(userName);
        //CustomLogger.setDebugMessage(String.valueOf(product));
        if (Objects.equals(user.getUserRole(), UserRole.Admin.toString())) {
            return productRepo.save(product);
        }else  throw new CustomGlobalException("User is not an admin",true,false);
    }
    @Override
    public List<Product> getAllProducts() throws CustomGlobalException {
        List<Product> products= productRepo.findAll();
        if(!products.isEmpty()){
            return products;
        }else throw new CustomGlobalException("Products are not available",false,true);
    }
    @Override
    public Product getProduct(int productId) throws CustomGlobalException {
        Product product =productRepo.findById(productId).orElse(null);
        if(product != null){
            return product;
        }else throw new CustomGlobalException("Product is not available",false,true);

    }
    @Override
    public Object deleteProduct(int productId, String userName) throws CustomGlobalException {
        //check user is admin or not with userService utility
        User user = userService.getUser(userName);
        if (Objects.equals(user.getUserRole(), UserRole.Admin.toString())) {
            //check product available or not
            Product product = this.getProduct(productId);
            productRepo.deleteById(product.getId());
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonObject = objectMapper.createObjectNode();
            jsonObject.put("productId", productId);
            jsonObject.put("message", "Product deleted successfully");
            return jsonObject;
        }else  throw new CustomGlobalException("User is not an admin",true,false);
    }

    @Override
    public Product updateProduct(Product product, String userName) throws CustomGlobalException {
        //check user is admin or not with userService utility
        User user = userService.getUser(userName);
        if (Objects.equals(user.getUserRole(), UserRole.Admin.toString()) && product != null) {
            Product existingProduct = this.getProduct(product.getId());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setUrl(product.getUrl());
            return productRepo.save(existingProduct);
        }else  throw new CustomGlobalException("User is not an admin",true,false);
    }
}
