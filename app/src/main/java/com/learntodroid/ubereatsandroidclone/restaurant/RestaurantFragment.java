package com.learntodroid.ubereatsandroidclone.restaurant;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.learntodroid.ubereatsandroidclone.R;
import com.learntodroid.ubereatsandroidclone.home.HomeViewModel;
import com.learntodroid.ubereatsandroidclone.home.Restaurant;

public class RestaurantFragment extends Fragment {
    private RestaurantViewModel restaurantViewModel;
    private TextView restaurantTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);
        restaurantViewModel.getSelectedRestaurantMutableLiveData().observe(this, new Observer<Restaurant>() {
            @Override
            public void onChanged(Restaurant restaurant) {
                if (restaurant != null) {
                    Log.i("Restaurant", restaurant.getTitle());
                    restaurantTextView.setText(restaurant.getTitle());
                }
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);

        restaurantTextView = view.findViewById(R.id.fragment_restaurant_restaurantTextView);

        return view;
    }
}
