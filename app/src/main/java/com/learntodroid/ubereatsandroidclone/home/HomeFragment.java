package com.learntodroid.ubereatsandroidclone.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learntodroid.ubereatsandroidclone.R;

import java.util.List;

public class HomeFragment extends Fragment implements OnRestaurantClickListener {
    private HomeViewModel homeViewModel;
    private RestaurantRecyclerAdapter restaurantRecyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        restaurantRecyclerAdapter = new RestaurantRecyclerAdapter(this);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getRestaurantsMutableLiveData().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {
                if (restaurants != null) {
                    restaurantRecyclerAdapter.setRestaurants(restaurants);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView restaurantsRecyclerView = view.findViewById(R.id.fragment_home_restaurants);
        restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        restaurantsRecyclerView.setAdapter(restaurantRecyclerAdapter);

        return view;
    }

    @Override
    public void restaurantClick(Restaurant restaurant) {
        homeViewModel.setSelectedRestaurant(restaurant);
        Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_restaurantFragment);
    }
}
