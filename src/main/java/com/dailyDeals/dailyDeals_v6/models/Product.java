package com.dailyDeals.dailyDeals_v6.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name = "PRODUCT")
public class Product{

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private int id;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "Price", nullable = false)
    private double Price;
    @Column(name = "Url", nullable = false)
    private String Url;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
