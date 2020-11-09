package com.learntodroid.ubereatsandroidclone.loginsignup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.learntodroid.ubereatsandroidclone.UberEatsRepository;

public class LoginSignUpViewModel extends ViewModel {
    private UberEatsRepository uberEatsRepository;
    private MutableLiveData<FirebaseUser> userLiveData;

    public LoginSignUpViewModel() {
        uberEatsRepository = UberEatsRepository.getInstance();
        userLiveData = uberEatsRepository.getUserLiveData();
    }

    public void login(String email, String password) {
        uberEatsRepository.login(email, password);
    }

    public void signUp(String email, String password) {
        uberEatsRepository.signUp(email, password);
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }
}
