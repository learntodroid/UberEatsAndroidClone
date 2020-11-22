package com.learntodroid.ubereatsandroidclone.restaurant;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.learntodroid.ubereatsandroidclone.loginsignup.UberEatsRepository;
import com.learntodroid.ubereatsandroidclone.home.Restaurant;
import com.learntodroid.ubereatsandroidclone.menuitemdetails.ShoppingCart;

import java.util.List;

public class RestaurantViewModel extends ViewModel {
    private UberEatsRepository uberEatsRepository;
    private MutableLiveData<Restaurant> selectedRestaurantMutableLiveData;
    private MutableLiveData<List<MenuItem>> menuItemsMutableLiveData;
    private MutableLiveData<ShoppingCart> shoppingCartMutableLiveData;

    public RestaurantViewModel() {
        uberEatsRepository = UberEatsRepository.getInstance();
        this.selectedRestaurantMutableLiveData = uberEatsRepository.getSelectedRestaurantMutableLiveData();
        this.menuItemsMutableLiveData = uberEatsRepository.getMenuItemsMutableLiveData();
        this.shoppingCartMutableLiveData = uberEatsRepository.getShoppingCartMutableLiveData();
        uberEatsRepository.queryMenuItems();
    }

    public void setSelectedMenuItem(MenuItem menuItem) {
        uberEatsRepository.setSelectedMenuItem(menuItem);
    }

    public MutableLiveData<Restaurant> getSelectedRestaurantMutableLiveData() {
        return selectedRestaurantMutableLiveData;
    }

    public MutableLiveData<List<MenuItem>> getMenuItemsMutableLiveData() {
        return menuItemsMutableLiveData;
    }

    public MutableLiveData<ShoppingCart> getShoppingCartMutableLiveData() {
        return shoppingCartMutableLiveData;
    }
}
