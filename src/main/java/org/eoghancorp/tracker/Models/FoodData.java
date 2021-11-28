package org.eoghancorp.tracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
public class FoodData {
    private String foodId;
    private String label;
    private FoodNutrients nutrients;
    private String category;
    private String categoryLabel;
    private String image;

    public FoodNutrients getNutrients() {
        return nutrients;
    }

    public String getCategory() {
        return category;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public String getFoodId() {
        return foodId;
    }

    public String getImage() {
        return image;
    }

    public String getLabel() {
        return label;
    }

    public void setNutrients(FoodNutrients nutrients) {
        this.nutrients = nutrients;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}

class FoodNutrients {

    private double ENERC_KCAL;
    private double PROCNT;
    private double FAT;
    private double CHOCDF;
    private double FIBTG;

    public double getCHOCDF() {
        return CHOCDF;
    }

    public double getENERC_KCAL() { return ENERC_KCAL; }

    public double getFAT() {
        return FAT;
    }

    public double getFIBTG() {
        return FIBTG;
    }

    public double getPROCNT() {
        return PROCNT;
    }

    @JsonProperty("CHOCDF")
    public void setCHOCDF(double CHOCDF) {
        this.CHOCDF = CHOCDF;
    }

    @JsonProperty("ENERC_KCAL")
    public void setENERC_KCAL(double ENERC_KCAL) { this.ENERC_KCAL = ENERC_KCAL; }

    @JsonProperty("FAT")
    public void setFAT(double FAT) {
        this.FAT = FAT;
    }

    @JsonProperty("FIBTG")
    public void setFIBTG(double FIBTG) {
        this.FIBTG = FIBTG;
    }

    @JsonProperty("PROCNT")
    public void setPROCNT(double PROCNT) {
        this.PROCNT = PROCNT;
    }
}
