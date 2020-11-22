package com.learntodroid.ubereatsandroidclone.menuitemdetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.learntodroid.ubereatsandroidclone.loginsignup.UberEatsRepository;
import com.learntodroid.ubereatsandroidclone.restaurant.MenuItem;

public class MenuItemDetailsViewModel extends ViewModel {
    private UberEatsRepository uberEatsRepository;
    private MutableLiveData<MenuItem> selectedMenuItemMutableLiveData;

    public MenuItemDetailsViewModel() {
        uberEatsRepository = UberEatsRepository.getInstance();
        this.selectedMenuItemMutableLiveData = uberEatsRepository.getSelectedMenuItemMutableLiveData();
    }

    public void addToShoppingCart(MenuItem menuItem, int quantity, String notes) {
        uberEatsRepository.addToShoppingCart(menuItem, quantity, notes);
    }

    public MutableLiveData<MenuItem> getSelectedMenuItemMutableLiveData() {
        return selectedMenuItemMutableLiveData;
    }
}
