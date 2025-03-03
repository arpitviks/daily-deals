package com.dailyDeals.dailyDeals_v6.models;

import com.dailyDeals.dailyDeals_v6.enums.DealStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Component
@Table(name = "DEAL")
public class Deal {
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public DealStatus getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public int getDealQuantity() {
        return dealQuantity;
    }

    public void setDealQuantity(int dealQuantity) {
        this.dealQuantity = dealQuantity;
    }

    @GeneratedValue
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private int dealId;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "active_hour", nullable = false)
    private int activeHour = 1;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "deal_status", nullable = false)
    private DealStatus dealStatus;

    @ManyToOne
    @JoinColumn(name = "fk_prod_id")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "fk_seller_id")
    private User seller;

    @Column(name = "deal_quantity", nullable = false)
    private int dealQuantity = 0;

    public int getActiveHour() {
        return activeHour;
    }

    public void setActiveHour(int activeHour) {
        this.activeHour = activeHour;
    }

}
