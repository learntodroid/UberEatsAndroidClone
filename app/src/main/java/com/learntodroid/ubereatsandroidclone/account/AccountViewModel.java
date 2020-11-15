package com.learntodroid.ubereatsandroidclone.account;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.learntodroid.ubereatsandroidclone.UberEatsRepository;

public class AccountViewModel extends ViewModel {
    private UberEatsRepository uberEatsRepository;
    private MutableLiveData<FirebaseUser> userLiveData;
    private MutableLiveData<UberEatsAccount> accountLiveData;

    public AccountViewModel() {
        uberEatsRepository = UberEatsRepository.getInstance();
        userLiveData = uberEatsRepository.getUserLiveData();
        accountLiveData = uberEatsRepository.getAccountLiveData();
        uberEatsRepository.queryAccount();
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<UberEatsAccount> getAccountLiveData() {
        return accountLiveData;
    }
}
