package com.dailyDeals.dailyDeals_v6.services;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.enums.UserRole;
import com.dailyDeals.dailyDeals_v6.models.Deal;
import com.dailyDeals.dailyDeals_v6.models.Product;
import com.dailyDeals.dailyDeals_v6.models.User;
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
    public Deal addDeal(Deal deal, String userName) throws CustomGlobalException{
        User user = userService.getUser(userName);
        if(Objects.equals(user.getUserRole(), UserRole.Admin.toString())){
            Product product = productService.getProduct(deal.getProduct().getId());
            deal.setProduct(product);
            return dealCustomRepo.addDeal(deal,user);
        } else throw new CustomGlobalException("User is not an admin",true,false);
    }
    @Override
    public List<Deal> getAllDeals() throws CustomGlobalException {
        return dealCustomRepo.getAllDeals();
    }
    @Override
    public Deal getDeal(int dealId) throws CustomGlobalException{
        return dealCustomRepo.getDeal(dealId);
    }
    @Override
    public Object deleteDeal(int dealId, String userName) throws CustomGlobalException {
        User user = userService.getUser(userName);
        if (Objects.equals(user.getUserRole(), UserRole.Admin.toString())) {
            return dealCustomRepo.deleteDeal(dealId);
        } else throw new CustomGlobalException("User is not an admin",true,false);
    }

    @Override
    public Deal updateDeal(Deal deal, String userName) throws CustomGlobalException, IllegalAccessException {
        //check user is admin or not with userService utility
        User user = userService.getUser(userName);
        if (Objects.equals(user.getUserRole(), UserRole.Admin.toString()) && deal != null) {
            Product product = productService.getProduct(deal.getProduct().getId());
           return dealCustomRepo.updateDeal(deal, product);
        }else  throw new CustomGlobalException("User is not an admin",true,false);
    }
    @Override
    public Deal increaseDealQuantityForAdmins(int dealId, String userName){
        User user = userService.getUser(userName);
        if (Objects.equals(user.getUserRole(), UserRole.Admin.toString())) {
            return dealCustomRepo.increaseDealQuantity(dealId);
        }else throw new CustomGlobalException("User is not an admin",true,false);
    }

    @Override
    public Deal decreaseDealQuantityForAdmins(int dealId, String userName){
        User user = userService.getUser(userName);
        if (Objects.equals(user.getUserRole(), UserRole.Admin.toString())) {
            return dealCustomRepo.decreaseDealQuantity(dealId);
        }else throw new CustomGlobalException("User is not an admin",true,false);
    }

    @Override
    public Deal increaseDealQuantity(int dealId){
        return dealCustomRepo.increaseDealQuantity(dealId);
    }
    @Override
    public Deal decreaseDealQuantity(int dealId){
        return dealCustomRepo.decreaseDealQuantity(dealId);
    }
}
