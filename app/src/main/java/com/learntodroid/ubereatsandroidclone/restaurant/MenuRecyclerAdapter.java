package com.learntodroid.ubereatsandroidclone.restaurant;

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
import java.util.List;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.MenuItemRecyclerViewHolder> {
    private List<MenuItem> menuItems;

    @NonNull
    @Override
    public MenuItemRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menuitem, parent, false);
        return new MenuItemRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemRecyclerViewHolder holder, int position) {
        holder.bind(menuItems.get(position));
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        notifyDataSetChanged();
    }

    class MenuItemRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description, price;
        private ImageView image;

        public MenuItemRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_menuitem_title);
            description = itemView.findViewById(R.id.item_menuitem_description);
            price = itemView.findViewById(R.id.item_menuitem_price);
            image = itemView.findViewById(R.id.item_menuitem_image);
        }

        public void bind(MenuItem menuItem) {
            title.setText(menuItem.getTitle());
            description.setText(menuItem.getDescription());
            price.setText(NumberFormat.getCurrencyInstance().format(menuItem.getPrice()));

            Glide.with(itemView)
                    .load(menuItem.getImageUri())
                    .fitCenter()
                    .into(image);
        }
    }
}
