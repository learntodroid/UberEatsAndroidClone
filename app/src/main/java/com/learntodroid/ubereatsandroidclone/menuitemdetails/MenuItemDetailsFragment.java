package com.learntodroid.ubereatsandroidclone.menuitemdetails;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.learntodroid.ubereatsandroidclone.R;
import com.learntodroid.ubereatsandroidclone.restaurant.MenuItem;

import java.text.NumberFormat;

public class MenuItemDetailsFragment extends Fragment {
    private MenuItemDetailsViewModel menuItemDetailsViewModel;
    private ImageView imageView;
    private TextView titleTextView, descriptionTextView;
    private EditText notesEditText, quantityEditText;
    private Button addItemButton, backButton;
    private MenuItem menuItem;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuItemDetailsViewModel = new ViewModelProvider(this).get(MenuItemDetailsViewModel.class);

        menuItemDetailsViewModel.getSelectedMenuItemMutableLiveData().observe(this, new Observer<MenuItem>() {
            @Override
            public void onChanged(MenuItem m) {
                if (m != null) {
                    menuItem = m;

                    titleTextView.setText(m.getTitle());
                    descriptionTextView.setText(m.getDescription());
                    quantityEditText.setText("1");
                    addItemButton.setText(String.format("Add %d to cart for %s", 1, NumberFormat.getCurrencyInstance().format(m.getPrice())));

                    Glide.with(getContext())
                            .load(m.getImageUri())
                            .fitCenter()
                            .into(imageView);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menuitemdetails, container, false);

        imageView = view.findViewById(R.id.fragment_menuitemdetails_image);
        titleTextView = view.findViewById(R.id.fragment_menuitemdetails_title);
        descriptionTextView = view.findViewById(R.id.fragment_menuitemdetails_description);
        notesEditText = view.findViewById(R.id.fragment_menuitemdetails_notes);
        quantityEditText = view.findViewById(R.id.fragment_menuitemdetails_quantity);
        addItemButton = view.findViewById(R.id.fragment_menuitemdetails_addtocart);
        backButton = view.findViewById(R.id.fragment_menuitemdetails_back);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(quantityEditText.getText().toString());
                menuItemDetailsViewModel.addToShoppingCart(menuItem, qty);
                Navigation.findNavController(getView()).popBackStack();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getView()).popBackStack();
            }
        });

        quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    int qty = Integer.parseInt(charSequence.toString());
                    addItemButton.setText(String.format("Add %d to cart for %s", qty, NumberFormat.getCurrencyInstance().format(qty * menuItem.getPrice())));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }
}
