package com.learntodroid.ubereatsandroidclone;

import androidx.lifecycle.MutableLiveData;

import com.learntodroid.ubereatsandroidclone.home.Restaurant;
import com.learntodroid.ubereatsandroidclone.search.Category;

import java.util.ArrayList;
import java.util.List;

public class UberEatsRepository {
    private static final UberEatsRepository instance = new UberEatsRepository();

    private MutableLiveData<List<Category>> categoriesMutableLiveData;
    private MutableLiveData<List<Restaurant>> restaurantMutableLiveData;
    private MutableLiveData<Restaurant> selectedRestaurantMutableLiveData;

    private UberEatsRepository() {
        this.categoriesMutableLiveData = new MutableLiveData<>();
        this.restaurantMutableLiveData = new MutableLiveData<>();
        this.selectedRestaurantMutableLiveData = new MutableLiveData<>();
    }

    public void queryCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Breakfast and Brunch", "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        categories.add(new Category("Burger", "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        categories.add(new Category("Grocery", "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        categories.add(new Category("Asian", "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        categories.add(new Category("Vietnamese", "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        categories.add(new Category("Mexican", "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        categories.add(new Category("Pizza", "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        categories.add(new Category("Chinese", "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        categories.add(new Category("Alcohol", "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        categories.add(new Category("Fish and chips", "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        categoriesMutableLiveData.postValue(categories);
    }

    public void queryRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("McDonald's", 5.99, 15, 35, 4.2, "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        restaurants.add(new Restaurant("Pizza Hut", 5.99, 15, 35, 4.2, "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        restaurants.add(new Restaurant("Hungry Jacks", 5.99, 15, 35, 4.2, "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        restaurants.add(new Restaurant("KFC", 5.99, 15, 35, 4.2, "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        restaurants.add(new Restaurant("Taco Bell", 5.99, 15, 35, 4.2, "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        restaurantMutableLiveData.postValue(restaurants);
    }

    public void setSelectedRestaurant(Restaurant restaurant) {
        selectedRestaurantMutableLiveData.postValue(restaurant);
    }

    public MutableLiveData<List<Category>> getCategoriesMutableLiveData() {
        return categoriesMutableLiveData;
    }

    public MutableLiveData<List<Restaurant>> getRestaurantMutableLiveData() {
        return restaurantMutableLiveData;
    }

    public MutableLiveData<Restaurant> getSelectedRestaurantMutableLiveData() {
        return selectedRestaurantMutableLiveData;
    }

    public static UberEatsRepository getInstance(){
        return instance;
    }
}
