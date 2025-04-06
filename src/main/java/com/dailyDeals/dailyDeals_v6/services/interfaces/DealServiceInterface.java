package com.dailyDeals.dailyDeals_v6.services.interfaces;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.DealEntity;

import java.util.List;

public interface DealServiceInterface {
    DealEntity addDeal(DealEntity deal, String userName) throws CustomGlobalException;

    List<DealEntity> getAllDeals() throws CustomGlobalException;

    DealEntity getDeal(int dealId) throws CustomGlobalException;

    Object deleteDeal(int dealId, String userName) throws CustomGlobalException;

    DealEntity updateDeal(DealEntity deal, String userName) throws CustomGlobalException, IllegalAccessException;

    DealEntity increaseDealQuantityForAdmins(int dealId, String userName);

    DealEntity decreaseDealQuantityForAdmins(int dealId, String userName);

    DealEntity increaseDealQuantity(int dealId);

    DealEntity decreaseDealQuantity(int dealId);
}
