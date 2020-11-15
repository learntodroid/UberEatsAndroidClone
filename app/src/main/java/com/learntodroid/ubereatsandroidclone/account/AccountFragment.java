package com.learntodroid.ubereatsandroidclone.account;

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

import com.google.firebase.auth.FirebaseUser;
import com.learntodroid.ubereatsandroidclone.R;

public class AccountFragment extends Fragment {
    private TextView userTextView;
    private AccountViewModel accountViewModel;
    private AddressRecyclerAdapter addressRecyclerAdapter;
    private PaymentMethodRecyclerAdapter paymentMethodRecyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addressRecyclerAdapter = new AddressRecyclerAdapter();
        paymentMethodRecyclerAdapter = new PaymentMethodRecyclerAdapter();

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        userTextView = view.findViewById(R.id.fragment_account_user);

        RecyclerView addressesRecyclerView = view.findViewById(R.id.fragment_account_addresses);
        addressesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addressesRecyclerView.setAdapter(addressRecyclerAdapter);

        accountViewModel.getUserLiveData().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    userTextView.setText(firebaseUser.getEmail());
                }
            }
        });

        accountViewModel.getAccountLiveData().observe(getViewLifecycleOwner(), new Observer<UberEatsAccount>() {
            @Override
            public void onChanged(UberEatsAccount uberEatsAccount) {
                if (uberEatsAccount != null) {
                    addressRecyclerAdapter.setAddresses(uberEatsAccount.getAddresses());
                }
            }
        });

        view.findViewById(R.id.fragment_account_addaddress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getView()).navigate(R.id.action_accountFragment_to_addEditAddressFragment);
            }
        });

        view.findViewById(R.id.fragment_account_addpaymentmethod).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getView()).navigate(R.id.action_accountFragment_to_addEditPaymentMethodFragment);
            }
        });

        return view;
    }
}
