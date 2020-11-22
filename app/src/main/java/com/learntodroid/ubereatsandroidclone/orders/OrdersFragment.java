package com.learntodroid.ubereatsandroidclone.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.learntodroid.ubereatsandroidclone.R;

public class OrdersFragment extends Fragment {
    private OrdersFragmentStateAdapter ordersFragmentStateAdapter;
    private ViewPager2 viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ordersFragmentStateAdapter = new OrdersFragmentStateAdapter(this);
        viewPager = view.findViewById(R.id.fragment_orders_viewpager);
        viewPager.setAdapter(ordersFragmentStateAdapter);

        TabLayout tabLayout = view.findViewById(R.id.fragment_orders_tablayout);
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Delivered Orders");
                } else if (position == 1) {
                    tab.setText("Upcoming Orders");
                }
            }
        }).attach();
    }
}
