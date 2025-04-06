package com.dailyDeals.dailyDeals_v6.services;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.enums.UserRole;
import com.dailyDeals.dailyDeals_v6.models.DealEntity;
import com.dailyDeals.dailyDeals_v6.models.ProductEntity;
import com.dailyDeals.dailyDeals_v6.models.UserEntity;
import com.dailyDeals.dailyDeals_v6.repositories.DealCustomRepo;
import com.dailyDeals.dailyDeals_v6.services.interfaces.DealServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DealService implements DealServiceInterface {
    @Autowired
    private DealCustomRepo dealCustomRepo;
    @Autowired
    private UserService userService;

    @Autowired
    ProductService productService;

    @Override
    public DealEntity addDeal(DealEntity deal, String userName) throws CustomGlobalException{
        UserEntity user = userService.getUser(userName);
        if(Objects.equals(user.getUserRole(), UserRole.Admin.toString())){
            ProductEntity product = productService.getProduct(deal.getProduct().getId());
            deal.setProduct(product);
            return dealCustomRepo.addDeal(deal,user);
        } else throw new CustomGlobalException("User is not an admin",true,false);
    }
    @Override
    public List<DealEntity> getAllDeals() throws CustomGlobalException {
        return dealCustomRepo.getAllDeals();
    }
    @Override
    public DealEntity getDeal(int dealId) throws CustomGlobalException{
        return dealCustomRepo.getDeal(dealId);
    }
    @Override
    public Object deleteDeal(int dealId, String userName) throws CustomGlobalException {
        UserEntity user = userService.getUser(userName);
        if (Objects.equals(user.getUserRole(), UserRole.Admin.toString())) {
            return dealCustomRepo.deleteDeal(dealId);
        } else throw new CustomGlobalException("User is not an admin",true,false);
    }

    @Override
    public DealEntity updateDeal(DealEntity deal, String userName) throws CustomGlobalException, IllegalAccessException {
        //check user is admin or not with userService utility
        UserEntity user = userService.getUser(userName);
        if (Objects.equals(user.getUserRole(), UserRole.Admin.toString()) && deal != null) {
            ProductEntity product = productService.getProduct(deal.getProduct().getId());
           return dealCustomRepo.updateDeal(deal, product);
        }else  throw new CustomGlobalException("User is not an admin",true,false);
    }
    @Override
    public DealEntity increaseDealQuantityForAdmins(int dealId, String userName){
        UserEntity user = userService.getUser(userName);
        if (Objects.equals(user.getUserRole(), UserRole.Admin.toString())) {
            return dealCustomRepo.increaseDealQuantity(dealId);
        }else throw new CustomGlobalException("User is not an admin",true,false);
    }

    @Override
    public DealEntity decreaseDealQuantityForAdmins(int dealId, String userName){
        UserEntity user = userService.getUser(userName);
        if (Objects.equals(user.getUserRole(), UserRole.Admin.toString())) {
            return dealCustomRepo.decreaseDealQuantity(dealId);
        }else throw new CustomGlobalException("User is not an admin",true,false);
    }

    @Override
    public DealEntity increaseDealQuantity(int dealId){
        return dealCustomRepo.increaseDealQuantity(dealId);
    }
    @Override
    public DealEntity decreaseDealQuantity(int dealId){
        return dealCustomRepo.decreaseDealQuantity(dealId);
    }
}
