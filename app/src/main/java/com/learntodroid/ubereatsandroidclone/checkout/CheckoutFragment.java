package com.learntodroid.ubereatsandroidclone.checkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.learntodroid.ubereatsandroidclone.R;
import com.learntodroid.ubereatsandroidclone.account.Address;
import com.learntodroid.ubereatsandroidclone.account.UberEatsAccount;
import com.learntodroid.ubereatsandroidclone.home.Restaurant;
import com.learntodroid.ubereatsandroidclone.menuitemdetails.ShoppingCart;

public class CheckoutFragment extends Fragment implements OnMapReadyCallback {
    private CheckoutViewModel checkoutViewModel;
    private CartRecyclerAdapter cartRecyclerAdapter;
    private PricesRecyclerAdapter pricesRecyclerAdapter;
    private TextView restaurantTextView, estimatedDeliveryTextView, deliveryAddressTextView;
    private MapView deliveryMapView;

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
        deliveryMapView = view.findViewById(R.id.fragment_checkout_mapview);

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
                }
            }
        });

        checkoutViewModel.getAccountLiveData().observe(getViewLifecycleOwner(), new Observer<UberEatsAccount>() {
            @Override
            public void onChanged(UberEatsAccount uberEatsAccount) {
                if (uberEatsAccount != null) {
                    if (uberEatsAccount.getAddresses().size() > 0) {
                        deliveryAddressTextView.setText(String.format("Delivering to: %s", uberEatsAccount.getAddresses().get(0).getAddress()));
                    }
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

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(getResources().getString(R.string.google_maps_api_key));
        }
        deliveryMapView.onCreate(mapViewBundle);
        deliveryMapView.getMapAsync(this);

        view.findViewById(R.id.fragment_checkout_purchase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkoutViewModel.submitOrder();
                Navigation.findNavController(getView()).navigate(R.id.action_checkOutFragment_to_orderProgressFragment);
            }
        });

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Address deliveryAddress = checkoutViewModel.getAccountLiveData().getValue().getAddresses().get(0);
        Restaurant restaurant = checkoutViewModel.getSelectedRestaurantMutableLiveData().getValue();

        LatLng deliveryPosition = new LatLng(deliveryAddress.getLatitude(), deliveryAddress.getLongitude());
        LatLng restaurantPosition = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());

        googleMap.addMarker(
                new MarkerOptions()
                        .position(deliveryPosition)
                        .title(deliveryAddress.getAddressType())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                )
        );

        googleMap.addMarker(new MarkerOptions().position(restaurantPosition).title(restaurant.getTitle()));

        PolylineOptions polylineOptions = new PolylineOptions()
                .add(deliveryPosition)
                .add(restaurantPosition);

        googleMap.addPolyline(polylineOptions);

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(deliveryPosition, 15f)));
    }

    @Override
    public void onStart() {
        super.onStart();
        deliveryMapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        deliveryMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        deliveryMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        deliveryMapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        deliveryMapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        deliveryMapView.onDestroy();
    }
}
