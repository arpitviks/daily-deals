package com.dailyDeals.dailyDeals_v6.models;

import com.dailyDeals.dailyDeals_v6.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity
@Component
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOM_ORDER")
public class CustomOrder {

    @GeneratedValue
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private int orderId;

    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "fk_deal_id")
    private Deal deal;

    @ManyToOne
    @JoinColumn(name = "fk_customer_id")
    private User user;

    @Column(name = "purchaseDate", nullable = false)
    private LocalDate purchaseDate;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
