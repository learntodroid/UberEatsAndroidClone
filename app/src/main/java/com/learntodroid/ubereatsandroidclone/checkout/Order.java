package com.learntodroid.ubereatsandroidclone.checkout;

import com.learntodroid.ubereatsandroidclone.account.UberEatsAccount;
import com.learntodroid.ubereatsandroidclone.home.Restaurant;
import com.learntodroid.ubereatsandroidclone.menuitemdetails.ShoppingCart;

public class Order {
    private Restaurant restaurant;
    private UberEatsAccount account;
    private ShoppingCart cart;
    public String status;

    public Order() {

    }

    public Order(Restaurant restaurant, UberEatsAccount account, ShoppingCart cart, String status) {
        this.restaurant = restaurant;
        this.account = account;
        this.cart = cart;
        this.status = status;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public UberEatsAccount getAccount() {
        return account;
    }

    public void setAccount(UberEatsAccount account) {
        this.account = account;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
