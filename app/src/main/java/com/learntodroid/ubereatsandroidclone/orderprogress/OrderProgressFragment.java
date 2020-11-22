package com.learntodroid.ubereatsandroidclone.orderprogress;

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

import com.learntodroid.ubereatsandroidclone.R;
import com.learntodroid.ubereatsandroidclone.checkout.Order;

public class OrderProgressFragment extends Fragment {
    private OrderProgressViewModel orderProgressViewModel;
    private TextView orderIdTextView, orderStatusTextView, restaurantTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orderProgressViewModel = new ViewModelProvider(this).get(OrderProgressViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orderprogress, container, false);

        orderIdTextView = view.findViewById(R.id.fragment_orderprogress_orderid);
        orderStatusTextView = view.findViewById(R.id.fragment_orderprogress_orderstatus);
        restaurantTextView = view.findViewById(R.id.fragment_orderprogress_restaurant);

        orderProgressViewModel.getOrderLiveData().observe(getViewLifecycleOwner(), new Observer<Order>() {
            @Override
            public void onChanged(Order order) {
                if (order != null) {
                    orderStatusTextView.setText(order.getStatus());
                    restaurantTextView.setText(order.getRestaurant().getTitle());
                }
            }
        });

        orderProgressViewModel.getOrderIdLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String orderId) {
                if (orderId != null) {
                    orderIdTextView.setText(orderId);
                    orderProgressViewModel.watchOrderUpdates(getContext().getApplicationContext());
                }
            }
        });

        view.findViewById(R.id.fragment_orderprogress_progressorder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderProgressViewModel.progressOrder();
            }
        });

        return view;
    }
}
