package org.eoghancorp.tracker.Models;

import java.util.Date;
import java.util.UUID;

public class UserFood {
    private String FoodId;
    private UUID UserId;
    private double Quantity;
    private Date createdAt;


    public UserFood(String foodId, String userId, double quantity, Date createdAt) {
        // this.UserId = UUID.fromString(userId);
        this.FoodId = foodId;
        this.Quantity = quantity;
        this.createdAt = createdAt;
    }



    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        this.Quantity = quantity;
    }

    public String getFoodId() {
        return FoodId;
    }

    public void setFoodId(String foodId) {
        FoodId = foodId;
    }

    public UUID getUserId() {
        return UserId;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }
}
