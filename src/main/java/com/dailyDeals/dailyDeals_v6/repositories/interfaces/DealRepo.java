package com.dailyDeals.dailyDeals_v6.repositories.interfaces;

import com.dailyDeals.dailyDeals_v6.models.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepo extends JpaRepository<Deal,Integer> {
}
