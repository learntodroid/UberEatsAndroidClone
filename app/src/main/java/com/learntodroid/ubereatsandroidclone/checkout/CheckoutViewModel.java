package com.learntodroid.ubereatsandroidclone.checkout;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.learntodroid.ubereatsandroidclone.UberEatsRepository;
import com.learntodroid.ubereatsandroidclone.home.Restaurant;
import com.learntodroid.ubereatsandroidclone.menuitemdetails.ShoppingCart;

public class CheckoutViewModel extends ViewModel {
    private UberEatsRepository uberEatsRepository;
    private MutableLiveData<Restaurant> selectedRestaurantMutableLiveData;
    private MutableLiveData<ShoppingCart> shoppingCartMutableLiveData;

    public CheckoutViewModel() {
        uberEatsRepository = UberEatsRepository.getInstance();
        this.selectedRestaurantMutableLiveData = uberEatsRepository.getSelectedRestaurantMutableLiveData();
        this.shoppingCartMutableLiveData = uberEatsRepository.getShoppingCartMutableLiveData();
    }

    public MutableLiveData<Restaurant> getSelectedRestaurantMutableLiveData() {
        return selectedRestaurantMutableLiveData;
    }

    public MutableLiveData<ShoppingCart> getShoppingCartMutableLiveData() {
        return shoppingCartMutableLiveData;
    }
}
