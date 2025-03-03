package com.dailyDeals.dailyDeals_v6.controllers;

import com.dailyDeals.dailyDeals_v6.LoggingAspect.CustomLogger;
import com.dailyDeals.dailyDeals_v6.controllers.interfaces.ProductControllerInterface;
import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.dto.ApiError;
import com.dailyDeals.dailyDeals_v6.models.Product;
import com.dailyDeals.dailyDeals_v6.services.ProductService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController implements ProductControllerInterface {
    @Autowired
    ProductService productService;

    @PostMapping("/")
    @Override
    public ResponseEntity<Object> addProduct(@Valid @RequestBody Product product, BindingResult result) throws CustomGlobalException {
        if (result.hasErrors()) {
            // Handle validation errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError( HttpStatus.BAD_REQUEST,(result.getAllErrors()).toString(),new Date()));
        }
        //CustomLogger.setDebugMessage(String.valueOf(userId));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Product product1 = productService.addProduct(product,userName);
        return ResponseEntity.status(HttpStatus.OK).body(product1);
    }
    @GetMapping("/")
    @Override
    public ResponseEntity<Object> getAllProducts() throws CustomGlobalException {
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.CREATED).body(productList);
    }
    @DeleteMapping("/{productId}")
    @Override
    public ResponseEntity<Object> deleteProduct(@PathVariable int productId) throws CustomGlobalException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Object obj = productService.deleteProduct(productId, userName);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }
    @GetMapping("/{productId}")
    @Override
    public ResponseEntity<Object> getProduct(@PathVariable int productId) throws CustomGlobalException {
        Product product = productService.getProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
    @PatchMapping("/")
    @Override
    public ResponseEntity<Object> updateProduct(@RequestBody Product productToUpdate) throws CustomGlobalException, IllegalAccessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Product updatedProduct = productService.updateProduct(productToUpdate, userName);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);

    }
}
