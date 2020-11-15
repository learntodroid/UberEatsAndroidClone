package com.learntodroid.ubereatsandroidclone.account;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learntodroid.ubereatsandroidclone.R;

import java.util.ArrayList;
import java.util.List;

public class AddressRecyclerAdapter extends RecyclerView.Adapter<AddressRecyclerAdapter.AddressRecyclerViewHolder> {
    private List<Address> addresses;

    public AddressRecyclerAdapter() {
        addresses = new ArrayList<>();
    }

    @NonNull
    @Override
    public AddressRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
        return new AddressRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressRecyclerViewHolder holder, int position) {
        holder.bind(addresses.get(position));
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
        notifyDataSetChanged();
    }

    class AddressRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView addressTextView, addressTypeTextView, gpsTextView;

        public AddressRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            addressTextView = itemView.findViewById(R.id.item_address_address);
            addressTypeTextView = itemView.findViewById(R.id.item_address_addresstype);
            gpsTextView = itemView.findViewById(R.id.item_address_gps);
        }

        public void bind(Address address) {
            addressTextView.setText(address.getAddress());
            addressTypeTextView.setText(address.getAddressType());
            gpsTextView.setText(String.format("LatLng: %f, %f", address.getLatitude(), address.getLongitude()));
        }
    }
}
