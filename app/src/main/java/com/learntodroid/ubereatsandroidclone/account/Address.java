package com.learntodroid.ubereatsandroidclone.account;

import com.google.android.gms.maps.model.LatLng;

public class Address {
    private String address;
    private String addressType;
    private LatLng position;

    public Address(String address, LatLng position, String addressType) {
        this.address = address;
        this.position = position;
        this.addressType = addressType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }
}
