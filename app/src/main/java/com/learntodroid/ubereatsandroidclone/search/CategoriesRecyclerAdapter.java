package com.learntodroid.ubereatsandroidclone.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.learntodroid.ubereatsandroidclone.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoryRecyclerViewHolder> {
    private List<Category> categories;

    public CategoriesRecyclerAdapter() {
        categories = new ArrayList<>();
    }

    @NonNull
    @Override
    public CategoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewHolder holder, int position) {
        holder.bind(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    class CategoryRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView overlayTextView;
        private ImageView imageView;

        public CategoryRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            overlayTextView = itemView.findViewById(R.id.item_category_overlayText);
            imageView = itemView.findViewById(R.id.item_category_image);
        }

        public void bind(Category category) {
            overlayTextView.setText(category.getTitle());

            Glide.with(itemView)
                    .load(category.getImageUri())
                    .fitCenter()
                    .into(imageView);
        }
    }
}
