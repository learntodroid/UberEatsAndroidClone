package com.learntodroid.ubereatsandroidclone.orderprogress;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.learntodroid.ubereatsandroidclone.loginsignup.UberEatsRepository;
import com.learntodroid.ubereatsandroidclone.checkout.Order;

public class OrderProgressViewModel extends ViewModel {
    private UberEatsRepository uberEatsRepository;
    private MutableLiveData<Order> orderLiveData;
    private MutableLiveData<String> orderIdLiveData;

    public OrderProgressViewModel() {
        uberEatsRepository = UberEatsRepository.getInstance();
        orderLiveData = uberEatsRepository.getOrderMutableLiveData();
        orderIdLiveData = uberEatsRepository.getOrderIdMutableLiveData();
    }

    public void watchOrderUpdates(Context context) {
        uberEatsRepository.watchOrderUpdates(context);
    }

    public void progressOrder() {
        uberEatsRepository.progressOrder();
    }

    public MutableLiveData<Order> getOrderLiveData() {
        return orderLiveData;
    }

    public MutableLiveData<String> getOrderIdLiveData() {
        return orderIdLiveData;
    }
}
