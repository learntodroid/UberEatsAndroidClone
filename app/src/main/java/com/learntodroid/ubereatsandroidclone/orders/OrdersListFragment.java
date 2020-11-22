package com.learntodroid.ubereatsandroidclone.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.learntodroid.ubereatsandroidclone.R;

public class OrdersListFragment extends Fragment {
    public static final String ARG_ORDERS_CATEGORY = "ARG_ORDERS_CATEGORY";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orderslist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        ((TextView) view.findViewById(R.id.fragment_orderslist_text)).setText(Integer.toString(args.getInt(ARG_ORDERS_CATEGORY)));
    }
}
