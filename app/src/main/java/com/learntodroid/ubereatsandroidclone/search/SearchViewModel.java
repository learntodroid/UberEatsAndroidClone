package com.learntodroid.ubereatsandroidclone.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.learntodroid.ubereatsandroidclone.loginsignup.UberEatsRepository;

import java.util.List;

public class SearchViewModel extends ViewModel {
    private UberEatsRepository uberEatsRepository;
    private MutableLiveData<List<Category>> categoriesMutableLiveData;

    public SearchViewModel() {
        uberEatsRepository = UberEatsRepository.getInstance();
        this.categoriesMutableLiveData = uberEatsRepository.getCategoriesMutableLiveData();
        uberEatsRepository.queryCategories();
    }

    public MutableLiveData<List<Category>> getCategoriesMutableLiveData() {
        return categoriesMutableLiveData;
    }
}
