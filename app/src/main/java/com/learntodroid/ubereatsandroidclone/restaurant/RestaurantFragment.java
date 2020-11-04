package com.learntodroid.ubereatsandroidclone.restaurant;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.learntodroid.ubereatsandroidclone.R;
import com.learntodroid.ubereatsandroidclone.home.Restaurant;

import java.text.NumberFormat;
import java.util.List;

public class RestaurantFragment extends Fragment {
    private RestaurantViewModel restaurantViewModel;
    private MenuRecyclerAdapter menuRecyclerAdapter;
    private TextView titleTextView, categoriesTextView, statsTextView, addressTextView;
    private ImageView image;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuRecyclerAdapter = new MenuRecyclerAdapter();
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);

        restaurantViewModel.getSelectedRestaurantMutableLiveData().observe(this, new Observer<Restaurant>() {
            @Override
            public void onChanged(Restaurant r) {
                if (r != null) {
                    titleTextView.setText(r.getTitle());
                    categoriesTextView.setText(String.format("%s %s", r.getPriceCategory(), TextUtils.join(", ", r.getFoodCategories())));
                    statsTextView.setText(String.format("%d-%d min %.2f %s", r.getMinDeliveryTime(), r.getMaxDeliveryTime(), r.getUserRating(), NumberFormat.getCurrencyInstance().format(r.getDeliveryFee())));
                    addressTextView.setText(r.getAddress());

                    Glide.with(getContext())
                            .load(r.getImageUri())
                            .fitCenter()
                            .into(image);
                }
            }
        });

        restaurantViewModel.getMenuItemsMutableLiveData().observe(this, new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(List<MenuItem> menuItems) {
                if (menuItems != null) {
                    menuRecyclerAdapter.setMenuItems(menuItems);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);

        titleTextView = view.findViewById(R.id.fragment_restaurant_title);
        categoriesTextView = view.findViewById(R.id.fragment_restaurant_categories);
        statsTextView = view.findViewById(R.id.fragment_restaurant_stats);
        addressTextView = view.findViewById(R.id.fragment_restaurant_address);

        image = view.findViewById(R.id.fragment_restaurant_image);

        RecyclerView menuRecyclerView = view.findViewById(R.id.fragment_restaurant_menu);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        menuRecyclerView.setAdapter(menuRecyclerAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(menuRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        menuRecyclerView.addItemDecoration(itemDecoration);

        return view;
    }
}
