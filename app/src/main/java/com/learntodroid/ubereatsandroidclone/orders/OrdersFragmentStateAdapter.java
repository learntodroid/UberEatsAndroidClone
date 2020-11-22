package com.learntodroid.ubereatsandroidclone.orders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class OrdersFragmentStateAdapter extends FragmentStateAdapter {
    public static final String ORDERS_CATEGORY_DELIVERED = "ORDERS_CATEGORY_DELIVERED";
    public static final String ORDERS_CATEGORY_UPCOMING = "ORDERS_CATEGORY_UPCOMING";

    public OrdersFragmentStateAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new OrdersListFragment();
        Bundle args = new Bundle();
        if (position == 0) {
            args.putString(OrdersListFragment.ARG_ORDERS_CATEGORY, ORDERS_CATEGORY_DELIVERED);
        } else if (position == 1) {
            args.putString(OrdersListFragment.ARG_ORDERS_CATEGORY, ORDERS_CATEGORY_UPCOMING);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
