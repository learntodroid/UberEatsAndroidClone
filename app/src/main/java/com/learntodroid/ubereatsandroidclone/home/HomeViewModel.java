package com.learntodroid.ubereatsandroidclone.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.learntodroid.ubereatsandroidclone.UberEatsRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private UberEatsRepository uberEatsRepository;
    private MutableLiveData<List<Restaurant>> restaurantsMutableLiveData;

    public HomeViewModel() {
        uberEatsRepository = UberEatsRepository.getInstance();
        this.restaurantsMutableLiveData = uberEatsRepository.getRestaurantMutableLiveData();
        uberEatsRepository.queryRestaurants();
    }

    public void setSelectedRestaurant(Restaurant restaurant) {
        uberEatsRepository.setSelectedRestaurant(restaurant);
    }

    public MutableLiveData<List<Restaurant>> getRestaurantsMutableLiveData() {
        return restaurantsMutableLiveData;
    }
}
