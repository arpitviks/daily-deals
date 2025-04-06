package com.dailyDeals.dailyDeals_v6.repositories.interfaces;

import com.dailyDeals.dailyDeals_v6.models.DealEntity;
import com.dailyDeals.dailyDeals_v6.models.ProductEntity;
import com.dailyDeals.dailyDeals_v6.models.UserEntity;

import java.util.List;

public interface DealCustomRepoInterface {
    DealEntity addDeal(DealEntity deal, UserEntity user);

    List<DealEntity> getAllDeals();

    DealEntity getDeal(int dealId);

    Object deleteDeal(int dealId);

    DealEntity updateDeal(DealEntity deal, ProductEntity product) throws IllegalAccessException;

    DealEntity increaseDealQuantity(int dealId);

    DealEntity decreaseDealQuantity(int dealId);
}
