package com.dailyDeals.dailyDeals_v6.repositories.interfaces;

import com.dailyDeals.dailyDeals_v6.models.DealEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepo extends JpaRepository<DealEntity,Integer> {
}
