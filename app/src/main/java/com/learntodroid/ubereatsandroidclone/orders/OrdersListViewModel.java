package com.learntodroid.ubereatsandroidclone.orders;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.learntodroid.ubereatsandroidclone.checkout.Order;
import com.learntodroid.ubereatsandroidclone.loginsignup.UberEatsRepository;

import java.util.List;

public class OrdersListViewModel extends ViewModel {
    private UberEatsRepository uberEatsRepository;
    private MutableLiveData<List<Order>> ordersListLiveData;

    public OrdersListViewModel() {
        uberEatsRepository = UberEatsRepository.getInstance();
    }

    public void queryDeliveredOrders() {

    }

    public void queryUpcomingOrders() {

    }

    public MutableLiveData<List<Order>> getOrdersListLiveData() {
        return ordersListLiveData;
    }
}
