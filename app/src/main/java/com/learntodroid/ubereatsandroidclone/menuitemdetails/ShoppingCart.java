package com.learntodroid.ubereatsandroidclone.menuitemdetails;

import com.learntodroid.ubereatsandroidclone.restaurant.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addToCart(MenuItem item, int quantity) {
        boolean match = false;
        for (CartItem cartItem: cartItems) {
            if (cartItem.getMenuItem().equals(item)) {
                match = true;
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            }
        }

        if (!match) {
            cartItems.add(new CartItem(item, quantity));
        }
    }

    public double calculateTotalPrice() {
        double price = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            price += cartItems.get(i).getMenuItem().getPrice() * cartItems.get(i).getQuantity();
        }
        return price;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
