package com.learntodroid.ubereatsandroidclone.restaurant;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.learntodroid.ubereatsandroidclone.UberEatsRepository;
import com.learntodroid.ubereatsandroidclone.home.Restaurant;

public class RestaurantViewModel extends ViewModel {
    private UberEatsRepository uberEatsRepository;
    private MutableLiveData<Restaurant> selectedRestaurantMutableLiveData;

    public RestaurantViewModel() {
        uberEatsRepository = UberEatsRepository.getInstance();
        this.selectedRestaurantMutableLiveData = uberEatsRepository.getSelectedRestaurantMutableLiveData();
    }

    public MutableLiveData<Restaurant> getSelectedRestaurantMutableLiveData() {
        return selectedRestaurantMutableLiveData;
    }
}
