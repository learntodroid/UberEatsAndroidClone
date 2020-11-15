package com.learntodroid.ubereatsandroidclone.addeditaddress;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.learntodroid.ubereatsandroidclone.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AddEditAddressFragment extends Fragment {
    private AddEditAddressViewModel addEditAddressViewModel;
    private AutoCompleteTextView addressAutoComplete;
    private ArrayAdapter<String> addressPredictionsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addEditAddressViewModel = new ViewModelProvider(this).get(AddEditAddressViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addeditaddress, container, false);

        addressAutoComplete = view.findViewById(R.id.fragment_addeditaddress_address);
        addressAutoComplete.setThreshold(3);
        addressAutoComplete.setAdapter(null);
        addressAutoComplete.dismissDropDown();

        addressAutoComplete.addTextChangedListener(new TextWatcher() {
            private Timer timer = new Timer();
            private final long DELAY = 1000; // milliseconds

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(final Editable editable) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                if (editable.toString().length() > addressAutoComplete.getThreshold()) {
                                    addEditAddressViewModel.placesAutosuggest(editable.toString());
                                    Log.i("api request", "api request");
                                }
                            }
                        }, DELAY
                );
            }
        });

        addEditAddressViewModel.getPredictionMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<AutocompletePrediction>>() {
            @Override
            public void onChanged(List<AutocompletePrediction> autocompletePredictions) {
                if (autocompletePredictions != null && autocompletePredictions.size() > 0) {
                    List<String> predictions = new ArrayList<>();
                    for (AutocompletePrediction autocompletePrediction: autocompletePredictions) {
                        predictions.add(autocompletePrediction.getFullText(null).toString());
                    }
                    addressPredictionsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, predictions);
                    addressAutoComplete.setAdapter(addressPredictionsAdapter);
                    addressAutoComplete.showDropDown();
                }
            }
        });

        addressAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AutocompletePrediction selectedPrediction = addEditAddressViewModel.getPredictionMutableLiveData().getValue().get(i);
                addEditAddressViewModel.addAccountAddress(selectedPrediction, "");
                addEditAddressViewModel.clearPredictions();

                Toast.makeText(getContext(), selectedPrediction.getFullText(null), Toast.LENGTH_SHORT).show();

                Navigation.findNavController(getView()).popBackStack();
            }
        });

        return view;
    }
}
