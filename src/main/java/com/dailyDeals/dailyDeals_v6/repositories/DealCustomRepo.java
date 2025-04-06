package com.dailyDeals.dailyDeals_v6.repositories;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.models.DealEntity;
import com.dailyDeals.dailyDeals_v6.models.ProductEntity;
import com.dailyDeals.dailyDeals_v6.models.UserEntity;
import com.dailyDeals.dailyDeals_v6.repositories.interfaces.DealCustomRepoInterface;
import com.dailyDeals.dailyDeals_v6.repositories.interfaces.DealRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DealCustomRepo implements DealCustomRepoInterface {
    @Autowired
    private DealRepo dealRepo;

    @Override
    public DealEntity addDeal(DealEntity deal, UserEntity user) {
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
    public List<DealEntity> getAllDeals() {
        List<DealEntity> deals = dealRepo.findAll();
        if (!deals.isEmpty()) {
            return deals;
        }
        throw new CustomGlobalException("Deals are not available", false, true);
    }

    @Override
    public DealEntity getDeal(int dealId) {
        DealEntity deal = dealRepo.findById(dealId).orElse(null);
        if (deal != null) return deal;
        else throw new CustomGlobalException("Deal is not available", false, true);
    }

    @Override
    public Object deleteDeal(int dealId) {
        DealEntity deal = this.getDeal(dealId);
        dealRepo.deleteById(deal.getDealId());
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonObject = objectMapper.createObjectNode();
        jsonObject.put("dealId", dealId);
        jsonObject.put("message", "Deal deleted successfully");
        return jsonObject;
    }

    @Override
    public DealEntity updateDeal(DealEntity deal, ProductEntity product) throws IllegalAccessException {
        DealEntity existingDeal = this.getDeal(deal.getDealId());
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
    public DealEntity increaseDealQuantity(int dealId) {
        DealEntity existingDeal = this.getDeal(dealId);
        existingDeal.setDealQuantity(existingDeal.getDealQuantity() + 1);
        return dealRepo.save(existingDeal);
    }
    @Override
    public DealEntity decreaseDealQuantity(int dealId){
        DealEntity existingDeal = this.getDeal(dealId);
        if(existingDeal.getDealQuantity() > 0 ){
            existingDeal.setDealQuantity(existingDeal.getDealQuantity() - 1);
            return dealRepo.save(existingDeal);
        }else throw new CustomGlobalException("Deal quantity can't be decreased",false,true);
    }
}
