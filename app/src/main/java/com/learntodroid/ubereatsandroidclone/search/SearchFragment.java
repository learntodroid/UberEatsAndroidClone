package com.learntodroid.ubereatsandroidclone.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learntodroid.ubereatsandroidclone.R;

import java.util.List;

public class SearchFragment extends Fragment {
    private SearchViewModel searchViewModel;
    private CategoriesRecyclerAdapter categoriesRecyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoriesRecyclerAdapter = new CategoriesRecyclerAdapter();

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        searchViewModel.getCategoriesMutableLiveData().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                if (categories != null) {
                    Log.i("Categories", "Size: " + categories.size());
                    categoriesRecyclerAdapter.setCategories(categories);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView categoriesRecyclerView = view.findViewById(R.id.fragment_search_categories);
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        categoriesRecyclerView.setAdapter(categoriesRecyclerAdapter);

        return view;
    }
}
