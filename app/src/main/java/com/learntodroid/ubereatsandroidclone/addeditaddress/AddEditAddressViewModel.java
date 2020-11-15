package com.learntodroid.ubereatsandroidclone.addeditaddress;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.learntodroid.ubereatsandroidclone.UberEatsRepository;

import java.util.List;

public class AddEditAddressViewModel extends AndroidViewModel {
    private UberEatsRepository uberEatsRepository;
    private MutableLiveData<List<AutocompletePrediction>> predictionMutableLiveData;

    public AddEditAddressViewModel(@NonNull Application application) {
        super(application);
        uberEatsRepository = UberEatsRepository.getInstance();
        predictionMutableLiveData = uberEatsRepository.getPredictionMutableLiveData();
        uberEatsRepository.initializeGooglePlaces(application);
    }

    public void placesAutosuggest(String query) {
        uberEatsRepository.placesAutosuggest(query);
    }

    public void addAccountAddress(AutocompletePrediction prediction, String addressType) {
        uberEatsRepository.addAccountAddress(prediction, addressType);
    }

    public void clearPredictions() {
        uberEatsRepository.clearPredictions();
    }

    public MutableLiveData<List<AutocompletePrediction>> getPredictionMutableLiveData() {
        return predictionMutableLiveData;
    }
}
