package com.learntodroid.ubereatsandroidclone.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.learntodroid.ubereatsandroidclone.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRecyclerAdapter extends RecyclerView.Adapter<RestaurantRecyclerAdapter.RestaurantRecyclerViewHolder> {
    private OnRestaurantClickListener listener;
    private List<Restaurant> restaurants;

    public RestaurantRecyclerAdapter(OnRestaurantClickListener listener) {
        this.listener = listener;
        restaurants = new ArrayList<>();
    }

    @NonNull
    @Override
    public RestaurantRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantRecyclerViewHolder holder, int position) {
        holder.bind(restaurants.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }

    class RestaurantRecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title, deliveryInfo, rating;

        public RestaurantRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.item_restaurant_image);
            title = itemView.findViewById(R.id.item_restaurant_title);
            deliveryInfo = itemView.findViewById(R.id.item_restaurant_deliveryInfo);
            rating = itemView.findViewById(R.id.item_restaurant_rating);
        }

        public void bind(final Restaurant r) {
            title.setText(r.getTitle());

            deliveryInfo.setText(String.format("%s %d-%d min", NumberFormat.getCurrencyInstance().format(r.getDeliveryFee()), r.getMinDeliveryTime(), r.getMaxDeliveryTime()));
            rating.setText(String.format("%.2f", r.getUserRating()));

            Glide.with(itemView)
                    .load(r.getImageUri())
                    .fitCenter()
                    .into(image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.restaurantClick(r);
                }
            });
        }
    }
}