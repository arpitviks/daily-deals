package com.dailyDeals.dailyDeals_v6.services;

import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.enums.OrderStatus;
import com.dailyDeals.dailyDeals_v6.enums.UserRole;
import com.dailyDeals.dailyDeals_v6.models.CustomOrder;
import com.dailyDeals.dailyDeals_v6.models.Deal;
import com.dailyDeals.dailyDeals_v6.models.User;
import com.dailyDeals.dailyDeals_v6.repositories.OrderCustomRepo;
import com.dailyDeals.dailyDeals_v6.services.interfaces.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService implements OrderServiceInterface {
    @Autowired
    private OrderCustomRepo orderCustomRepo;

    @Autowired
    private DealService dealService;

    @Autowired
    private UserService userService;
    @Override
    public CustomOrder purchaseOrder(int dealId, String userName) throws CustomGlobalException {
        Deal deal = dealService.getDeal(dealId);
        User user = userService.getUser(userName);
        if(checkDealPurchasedByUser(user,deal) && deal.getDealQuantity() > 0 && deal.getEndTime().isAfter(LocalDateTime.now())) {//validate deal present or not and check user is already acquired the deal or not.
            synchronized (this) {
                dealService.decreaseDealQuantity(deal.getDealId());
                return orderCustomRepo.PurchaseOrder(deal, user);
                //user.setOrders(newOrder);

            }
        }else  throw  new CustomGlobalException("Deal is already expired or already purchased by an user",true,false);
    }
    @Override
    public List<CustomOrder> getAllOrder() throws CustomGlobalException {
        return orderCustomRepo.getAllOrder();
    }
    @Override
    public List<CustomOrder> getCustomerOrder(String userName) throws CustomGlobalException {
        User user = userService.getUser(userName);
        return orderCustomRepo.getCustomerOrders(user);
    }
    @Override
    public CustomOrder getOrder(int orderId) throws CustomGlobalException {
        return orderCustomRepo.getOrder(orderId);
    }
    @Override
    public CustomOrder cancelOrder(int orderId, String userName) throws CustomGlobalException {//check user present or not and order present else throw exception
        int dealId = this.getOrder(orderId).getDeal().getDealId();
        CustomOrder order = this.getOrder(orderId);
        User user1 = userService.getUser(userName);
        if(orderCustomRepo.checkOrderOwner(order,user1) && order.getOrderStatus() != OrderStatus.Canceled){
            dealService.increaseDealQuantity(dealId);
            return orderCustomRepo.cancelOrder(orderId);
        }else  throw new CustomGlobalException("Order shall be cancelled by an owner or an admin only",true,false);

    }
    @Override
    public CustomOrder updateOrder(int orderId, CustomOrder updatedOrder, String userName) throws CustomGlobalException, IllegalAccessException {
        if(Objects.equals(userService.getUser(userName).getUserRole(), UserRole.Admin.toString()) || Objects.equals(orderCustomRepo.getOrder(orderId).getUser().getUsername(), userName)){
            return orderCustomRepo.updateOrder(orderId,updatedOrder);
        } else throw new CustomGlobalException("User is not an owner or an admin",true,false);

    }
    //check user has already purchased the deal or not
    @Override
    public Boolean checkDealPurchasedByUser(User user, Deal deal) throws CustomGlobalException {
        return orderCustomRepo.checkDealPurchasedByUser(user,deal);
    }
}
