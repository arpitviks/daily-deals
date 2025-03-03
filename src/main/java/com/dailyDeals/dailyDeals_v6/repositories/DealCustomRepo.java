package com.dailyDeals.dailyDeals_v6.repositories;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.Deal;
import com.dailyDeals.dailyDeals_v6.models.Product;
import com.dailyDeals.dailyDeals_v6.models.User;
import com.dailyDeals.dailyDeals_v6.repositories.interfaces.DealCustomRepoInteface;
import com.dailyDeals.dailyDeals_v6.repositories.interfaces.DealRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DealCustomRepo implements DealCustomRepoInteface {
    @Autowired
    private DealRepo dealRepo;

    @Override
    public Deal addDeal(Deal deal, User user) {
        if (deal != null && deal.getDealQuantity() > 0) {
            deal.setStartTime(LocalDateTime.now());
            deal.setEndTime(LocalDateTime.now().plusHours(deal.getActiveHour()));
            deal.setStartTime(LocalDateTime.now());
            deal.setEndTime(LocalDateTime.now().plusHours(deal.getActiveHour()));
            deal.setSeller(user);
            return dealRepo.save(deal);
        } else throw new CustomGlobalException("Deal should not be null", false, true);
    }

    @Override
    public List<Deal> getAllDeals() {
        List<Deal> deals = dealRepo.findAll();
        if (!deals.isEmpty()) {
            return deals;
        }
        throw new CustomGlobalException("Deals are not available", false, true);
    }

    @Override
    public Deal getDeal(int dealId) {
        Deal deal = dealRepo.findById(dealId).orElse(null);
        if (deal != null) return deal;
        else throw new CustomGlobalException("Deal is not available", false, true);
    }

    @Override
    public Object deleteDeal(int dealId) {
        Deal deal = this.getDeal(dealId);
        dealRepo.deleteById(deal.getDealId());
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonObject = objectMapper.createObjectNode();
        jsonObject.put("dealId", dealId);
        jsonObject.put("message", "Deal deleted successfully");
        return jsonObject;
    }

    @Override
    public Deal updateDeal(Deal deal, Product product) throws IllegalAccessException {
        Deal existingDeal = this.getDeal(deal.getDealId());
        existingDeal.setDealStatus(deal.getDealStatus());
        existingDeal.setDealQuantity(deal.getDealQuantity());
        existingDeal.setProduct(product);

//        Field[] fields = existingDeal.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            Object newValue = field.get(deal);
//            Object oldValue = field.get(existingDeal);
//
//            if (newValue != null && !String.valueOf(field.getName()).equals("id") && !newValue.equals(oldValue)) {
//                field.set(existingDeal, newValue);
//            }
//        }
        return dealRepo.save(existingDeal);
    }

    @Override
    public Deal increaseDealQuantity(int dealId) {
        Deal existingDeal = this.getDeal(dealId);
        existingDeal.setDealQuantity(existingDeal.getDealQuantity() + 1);
        return dealRepo.save(existingDeal);
    }
    @Override
    public Deal decreaseDealQuantity(int dealId){
        Deal existingDeal = this.getDeal(dealId);
        if(existingDeal.getDealQuantity() > 0 ){
            existingDeal.setDealQuantity(existingDeal.getDealQuantity() - 1);
            return dealRepo.save(existingDeal);
        }else throw new CustomGlobalException("Deal quantity can't be decreased",false,true);
    }
}
