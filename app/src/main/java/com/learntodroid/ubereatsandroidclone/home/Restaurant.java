package com.learntodroid.ubereatsandroidclone.home;

import java.util.List;

public class Restaurant {
    private String title;
    private double deliveryFee;
    private int minDeliveryTime;
    private int maxDeliveryTime;
    private double userRating;
    private String imageUri;
    private String priceCategory;
    private List<String> foodCategories;
    private String address;

    public Restaurant(String title, double deliveryFee, int minDeliveryTime, int maxDeliveryTime, double userRating, String imageUri, String priceCategory, List<String> foodCategories, String address) {
        this.title = title;
        this.deliveryFee = deliveryFee;
        this.minDeliveryTime = minDeliveryTime;
        this.maxDeliveryTime = maxDeliveryTime;
        this.userRating = userRating;
        this.imageUri = imageUri;
        this.priceCategory = priceCategory;
        this.foodCategories = foodCategories;
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public int getMinDeliveryTime() {
        return minDeliveryTime;
    }

    public void setMinDeliveryTime(int minDeliveryTime) {
        this.minDeliveryTime = minDeliveryTime;
    }

    public int getMaxDeliveryTime() {
        return maxDeliveryTime;
    }

    public void setMaxDeliveryTime(int maxDeliveryTime) {
        this.maxDeliveryTime = maxDeliveryTime;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(String priceCategory) {
        this.priceCategory = priceCategory;
    }

    public List<String> getFoodCategories() {
        return foodCategories;
    }

    public void setFoodCategories(List<String> foodCategories) {
        this.foodCategories = foodCategories;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
