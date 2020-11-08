package com.learntodroid.ubereatsandroidclone.checkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learntodroid.ubereatsandroidclone.R;
import com.learntodroid.ubereatsandroidclone.home.Restaurant;
import com.learntodroid.ubereatsandroidclone.menuitemdetails.ShoppingCart;

public class CheckoutFragment extends Fragment {
    private CheckoutViewModel checkoutViewModel;
    private CartRecyclerAdapter cartRecyclerAdapter;
    private PricesRecyclerAdapter pricesRecyclerAdapter;
    private TextView restaurantTextView, estimatedDeliveryTextView, deliveryAddressTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cartRecyclerAdapter = new CartRecyclerAdapter();
        pricesRecyclerAdapter = new PricesRecyclerAdapter();
        checkoutViewModel = new ViewModelProvider(this).get(CheckoutViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        restaurantTextView = view.findViewById(R.id.fragment_checkout_restaurant);
        estimatedDeliveryTextView = view.findViewById(R.id.fragment_checkout_estimateddelivery);
        deliveryAddressTextView = view.findViewById(R.id.fragment_checkout_deliveryaddress);

        view.findViewById(R.id.fragment_checkout_additems).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getView()).navigate(R.id.action_checkOutFragment_to_restaurantFragment);
            }
        });

        checkoutViewModel.getSelectedRestaurantMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Restaurant>() {
            @Override
            public void onChanged(Restaurant r) {
                if (r != null) {
                    restaurantTextView.setText(r.getTitle());
                    estimatedDeliveryTextView.setText(String.format("Estimated Delivery Time: %d-%d mins", r.getMinDeliveryTime(), r.getMaxDeliveryTime()));
                    deliveryAddressTextView.setText(String.format("%s", "Your Delivery Address"));
                }
            }
        });

        checkoutViewModel.getShoppingCartMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ShoppingCart>() {
            @Override
            public void onChanged(ShoppingCart shoppingCart) {
                if (shoppingCart != null) {
                    cartRecyclerAdapter.setCartItems(shoppingCart.getCartItems());
                    pricesRecyclerAdapter.setPriceItems(shoppingCart.calculatePrices());
                }
            }
        });

        RecyclerView cartRecyclerView = view.findViewById(R.id.fragment_checkout_cart);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartRecyclerView.setAdapter(cartRecyclerAdapter);

        RecyclerView pricesRecyclerView = view.findViewById(R.id.fragment_checkout_price);
        pricesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pricesRecyclerView.setAdapter(pricesRecyclerAdapter);

        return view;
    }
}
