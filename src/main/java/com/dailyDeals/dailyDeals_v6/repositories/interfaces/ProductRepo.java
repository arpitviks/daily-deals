package com.dailyDeals.dailyDeals_v6.repositories.interfaces;

import com.dailyDeals.dailyDeals_v6.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
