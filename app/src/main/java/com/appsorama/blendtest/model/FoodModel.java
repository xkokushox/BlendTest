package com.appsorama.blendtest.model;

/**
 * Created by Jose Torres in Apps-O-Rama on 19/04/16.
 */
public class FoodModel {
    public String name;
    public String category;
    public StockModel stock_info;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category == null ? "" : category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public StockModel getStock_info() {
        return stock_info == null ? new StockModel() : stock_info;
    }

    public void setStock_info(StockModel stock_info) {
        this.stock_info = stock_info;
    }
}
