package com.learntodroid.ubereatsandroidclone.checkout;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.learntodroid.ubereatsandroidclone.loginsignup.UberEatsRepository;
import com.learntodroid.ubereatsandroidclone.account.UberEatsAccount;
import com.learntodroid.ubereatsandroidclone.home.Restaurant;
import com.learntodroid.ubereatsandroidclone.menuitemdetails.ShoppingCart;

public class CheckoutViewModel extends ViewModel {
    private UberEatsRepository uberEatsRepository;
    private MutableLiveData<Restaurant> selectedRestaurantMutableLiveData;
    private MutableLiveData<ShoppingCart> shoppingCartMutableLiveData;
    private MutableLiveData<UberEatsAccount> accountLiveData;

    public CheckoutViewModel() {
        uberEatsRepository = UberEatsRepository.getInstance();
        this.selectedRestaurantMutableLiveData = uberEatsRepository.getSelectedRestaurantMutableLiveData();
        this.shoppingCartMutableLiveData = uberEatsRepository.getShoppingCartMutableLiveData();
        uberEatsRepository.queryAccount();
        this.accountLiveData = uberEatsRepository.getAccountLiveData();
    }

    public void submitOrder() {
        uberEatsRepository.submitOrder();
    }

    public MutableLiveData<Restaurant> getSelectedRestaurantMutableLiveData() {
        return selectedRestaurantMutableLiveData;
    }

    public MutableLiveData<ShoppingCart> getShoppingCartMutableLiveData() {
        return shoppingCartMutableLiveData;
    }

    public MutableLiveData<UberEatsAccount> getAccountLiveData() {
        return accountLiveData;
    }
}
