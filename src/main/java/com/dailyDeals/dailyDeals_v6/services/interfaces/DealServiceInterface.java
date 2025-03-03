package com.dailyDeals.dailyDeals_v6.services.interfaces;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.Deal;

import java.util.List;

public interface DealServiceInterface {
    Deal addDeal(Deal deal, String userName) throws CustomGlobalException;

    List<Deal> getAllDeals() throws CustomGlobalException;

    Deal getDeal(int dealId) throws CustomGlobalException;

    Object deleteDeal(int dealId, String userName) throws CustomGlobalException;

    Deal updateDeal(Deal deal, String userName) throws CustomGlobalException, IllegalAccessException;

    Deal increaseDealQuantityForAdmins(int dealId, String userName);

    Deal decreaseDealQuantityForAdmins(int dealId, String userName);

    Deal increaseDealQuantity(int dealId);

    Deal decreaseDealQuantity(int dealId);
}
