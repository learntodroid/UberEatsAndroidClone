package com.learntodroid.ubereatsandroidclone.loginsignup;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.learntodroid.ubereatsandroidclone.R;
import com.learntodroid.ubereatsandroidclone.account.Address;
import com.learntodroid.ubereatsandroidclone.account.PaymentMethod;
import com.learntodroid.ubereatsandroidclone.account.UberEatsAccount;
import com.learntodroid.ubereatsandroidclone.checkout.Order;
import com.learntodroid.ubereatsandroidclone.home.Restaurant;
import com.learntodroid.ubereatsandroidclone.menuitemdetails.ShoppingCart;
import com.learntodroid.ubereatsandroidclone.restaurant.MenuItem;
import com.learntodroid.ubereatsandroidclone.search.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.learntodroid.ubereatsandroidclone.loginsignup.App.ORDERS_CHANNEL_ID;

public class UberEatsRepository {
    private static final UberEatsRepository instance = new UberEatsRepository();

    private MutableLiveData<List<Category>> categoriesMutableLiveData;
    private MutableLiveData<List<Restaurant>> restaurantMutableLiveData;
    private MutableLiveData<Restaurant> selectedRestaurantMutableLiveData;
    private MutableLiveData<List<MenuItem>> menuItemsMutableLiveData;
    private MutableLiveData<MenuItem> selectedMenuItemMutableLiveData;
    private MutableLiveData<ShoppingCart> shoppingCartMutableLiveData;

    private FirebaseAuth firebaseAuth;
    private MutableLiveData<FirebaseUser> userLiveData;

    private MutableLiveData<UberEatsAccount> accountLiveData;

    private PlacesClient placesClient;
    private MutableLiveData<List<AutocompletePrediction>> predictionMutableLiveData;

    private FirebaseFirestore db;
    private MutableLiveData<Order> orderMutableLiveData;
    private MutableLiveData<String> orderIdMutableLiveData;

    private UberEatsRepository() {
        this.categoriesMutableLiveData = new MutableLiveData<>();
        this.restaurantMutableLiveData = new MutableLiveData<>();
        this.selectedRestaurantMutableLiveData = new MutableLiveData<>();
        this.menuItemsMutableLiveData = new MutableLiveData<>();
        this.selectedMenuItemMutableLiveData = new MutableLiveData<>();
        this.shoppingCartMutableLiveData = new MutableLiveData<>(new ShoppingCart());

        this.firebaseAuth = FirebaseAuth.getInstance();
        this.userLiveData = new MutableLiveData<>();
        if (firebaseAuth.getCurrentUser() != null) {
            userLiveData.postValue(firebaseAuth.getCurrentUser());
        }

        this.accountLiveData = new MutableLiveData<>();

        this.predictionMutableLiveData = new MutableLiveData<>();

        this.db = FirebaseFirestore.getInstance();
        this.orderMutableLiveData = new MutableLiveData<>();
        this.orderIdMutableLiveData = new MutableLiveData<>();
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

//    public void queryRestaurants() {
//        List<Restaurant> restaurants = new ArrayList<>();
//
//        String img = "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260";
//
//        List<String> foodCategories = new ArrayList<>();
//        foodCategories.addAll(Arrays.asList(new String[]{"American", "Burger", "Fast Food", "Family Meals"}));
//
//        restaurants.add(new Restaurant("McDonald's", 5.99, 15, 35, 4.2, img, "$", foodCategories, "123 Fake Street, Melbourne", -33.880490, 151.184363));
//        restaurants.add(new Restaurant("Pizza Hut", 5.99, 15, 35, 4.2, img, "$", foodCategories, "123 Fake Street, Melbourne", -33.880490, 151.184363));
//        restaurants.add(new Restaurant("Hungry Jacks", 5.99, 15, 35, 4.2, img, "$", foodCategories, "123 Fake Street, Melbourne", -33.880490, 151.184363));
//        restaurants.add(new Restaurant("KFC", 5.99, 15, 35, 4.2, img, "$$", foodCategories, "123 Fake Street, Melbourne", -33.880490, 151.184363));
//        restaurants.add(new Restaurant("Taco Bell", 5.99, 15, 35, 4.2, img, "$$", foodCategories, "123 Fake Street, Melbourne", -33.880490, 151.184363));
//        restaurantMutableLiveData.postValue(restaurants);
//    }

    public void queryRestaurants() {
        final List<Restaurant> restaurants = new ArrayList<>();

        db.collection("restaurants")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(UberEatsRepository.class.getSimpleName(), document.getId() + " => " + document.getData());
                                Restaurant restaurant = document.toObject(Restaurant.class);
                                restaurants.add(restaurant);
                            }
                            restaurantMutableLiveData.postValue(restaurants);
                        } else {
                            Log.d(UberEatsRepository.class.getSimpleName(), "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void queryMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Egg", "A freshly laid egg", "https://images.pexels.com/photos/2271107/pexels-photo-2271107.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260", 0.99));
        menuItems.add(new MenuItem("Milk", "A bottle of full cream milk", "https://images.pexels.com/photos/2271107/pexels-photo-2271107.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",1.99));
        menuItems.add(new MenuItem("Cheese", "A block of tasty cheese", "https://images.pexels.com/photos/2271107/pexels-photo-2271107.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260", 5.99));
        menuItems.add(new MenuItem("Yogurt", "A cartoon of yogurt", "https://images.pexels.com/photos/2271107/pexels-photo-2271107.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260", 2.99));
        menuItemsMutableLiveData.postValue(menuItems);
    }

    public void addToShoppingCart(MenuItem menuItem, int quantity, String notes) {
        ShoppingCart shoppingCart = shoppingCartMutableLiveData.getValue();
        shoppingCart.addToCart(menuItem, quantity, notes);
        shoppingCart.setDeliveryFee(selectedRestaurantMutableLiveData.getValue().getDeliveryFee());
        shoppingCartMutableLiveData.postValue(shoppingCart);
    }

    public void setSelectedRestaurant(Restaurant restaurant) {
        selectedRestaurantMutableLiveData.postValue(restaurant);
    }

    public void setSelectedMenuItem(MenuItem menuItem) {
        selectedMenuItemMutableLiveData.postValue(menuItem);
    }

    public void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userLiveData.postValue(firebaseAuth.getCurrentUser());
                        }
                    }
                });
    }

    public void signUp(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userLiveData.postValue(firebaseAuth.getCurrentUser());
                        }
                    }
                });
    }

    public void queryAccount() {
        UberEatsAccount account = new UberEatsAccount(firebaseAuth.getUid());

        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("120 Fake St, Melbourne, Victoria, 3000, Australia", -33.881490, 151.184363, "Home"));
        addresses.add(new Address("500 Fake St, Melbourne, Victoria, 3000, Australia", -33.858754, 151.229596, "Work"));
        account.setAddresses(addresses);

        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(new PaymentMethod());

        accountLiveData.postValue(account);
    }

    public void initializeGooglePlaces(Application application) {
        if (!Places.isInitialized()) {
            Places.initialize(application.getApplicationContext(), application.getResources().getString(R.string.google_places_api_key));
            this.placesClient = Places.createClient(application.getApplicationContext());
        }
    }

    public void placesAutosuggest(String query) {
        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();

//        RectangularBounds bounds = RectangularBounds.newInstance(
//                new LatLng(-33.880490, 151.184363),
//                new LatLng(-33.858754, 151.229596));

        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                // Call either setLocationBias() OR setLocationRestriction().
//                .setLocationBias(bounds)
                //.setLocationRestriction(bounds)
//                .setCountry("au")
                .setTypeFilter(TypeFilter.ADDRESS)
                .setSessionToken(token)
                .setQuery(query)
                .build();

        placesClient.findAutocompletePredictions(request).addOnSuccessListener(new OnSuccessListener<FindAutocompletePredictionsResponse>() {
            @Override
            public void onSuccess(FindAutocompletePredictionsResponse response) {
                predictionMutableLiveData.postValue(response.getAutocompletePredictions());
                for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {

                    Log.i("placesAutosuggest", prediction.getPlaceId());
                    Log.i("placesAutosuggest", prediction.getPrimaryText(null).toString());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                if (exception instanceof ApiException) {
                    ApiException apiException = (ApiException) exception;
                    Log.e("placesAutosuggest", "Place not found: " + apiException.getStatusCode());
                }
            }
        });
    }

    public void clearPredictions() {
        predictionMutableLiveData.postValue(new ArrayList<AutocompletePrediction>());
    }

    public void addAccountAddress(final AutocompletePrediction prediction, final String addressType) {
        final List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
        final FetchPlaceRequest request = FetchPlaceRequest.newInstance(prediction.getPlaceId(), placeFields);
        placesClient.fetchPlace(request)
                .addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
                    @Override
                    public void onSuccess(FetchPlaceResponse response) {
                        Place place = response.getPlace();
                        place.getLatLng();
                        Log.i("Fetch Place", "Place found: " + place.getName());

                        UberEatsAccount account = accountLiveData.getValue();
                        List<Address> addresses = account.getAddresses();

                        addresses.add(new Address(prediction.getFullText(null).toString(), place.getLatLng().latitude, place.getLatLng().longitude, addressType));

                        account.setAddresses(addresses);
                        accountLiveData.postValue(account);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        if (exception instanceof ApiException) {
                            Log.e("Fetch Place", "Place not found: " + exception.getMessage());
                        }
                    }
                });
    }

    public void submitOrder() {
        Restaurant restaurant = selectedRestaurantMutableLiveData.getValue();
        UberEatsAccount account = accountLiveData.getValue();
        ShoppingCart shoppingCart = shoppingCartMutableLiveData.getValue();

        Order order = new Order(restaurant, account, shoppingCart, "New");

        orderMutableLiveData.postValue(order);

        db.collection("orders")
                .add(order)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(UberEatsRepository.class.getSimpleName(), "DocumentSnapshot added with ID: " + documentReference.getId());
                        orderIdMutableLiveData.postValue(documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(UberEatsRepository.class.getSimpleName(), "Error adding document", e);
                    }
                });
    }

    public void watchOrderUpdates(final Context context) {
        final DocumentReference docRef = db.collection("orders").document(orderIdMutableLiveData.getValue());
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.i(UberEatsRepository.class.getSimpleName(), "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.i(UberEatsRepository.class.getSimpleName(), "Current data: " + snapshot.getData());
                    Order order = snapshot.toObject(Order.class);
                    orderMutableLiveData.postValue(order);
                    orderStatusNotification(context);
                } else {
                    Log.i(UberEatsRepository.class.getSimpleName(), "Current data: null");
                }
            }
        });
    }

    public void progressOrder() {
        Restaurant restaurant = selectedRestaurantMutableLiveData.getValue();
        UberEatsAccount account = accountLiveData.getValue();
        ShoppingCart shoppingCart = shoppingCartMutableLiveData.getValue();
        Order order = orderMutableLiveData.getValue();
        String newStatus = "Delivered";

        switch (order.getStatus()) {
            case "New":
                newStatus = "Preparing";
                break;
            case "Preparing":
                newStatus = "Awaiting Collection";
                break;
            case "Awaiting Collection":
                newStatus = "Delivering";
                break;
            case "Delivering":
                newStatus = "Delivered";
                break;
        }

        order.setStatus(newStatus);

        DocumentReference orderRef = db.collection("orders").document(orderIdMutableLiveData.getValue());
        orderRef
                .set(order)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(UberEatsRepository.class.getSimpleName(), "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(UberEatsRepository.class.getSimpleName(), "Error updating document", e);
                    }
                });
    }

    public void orderStatusNotification(Context context) {
        Order order = orderMutableLiveData.getValue();
        if (order.getNotifications().get(order.getStatus()) == false) {
            // send notification
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            Notification notification = new NotificationCompat.Builder(context, ORDERS_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_baseline_fastfood_24)
                    .setContentTitle("Order " + order.getStatus())
                    .setContentText(String.format("Order from %s Progressed to %s", selectedRestaurantMutableLiveData.getValue().getTitle(), order.getStatus()))
                    .setCategory(NotificationCompat.CATEGORY_PROGRESS)
                    .build();

            notificationManagerCompat.notify(1, notification);

            // update notification status
            order.getNotifications().put(order.getStatus(), true);
            DocumentReference orderRef = db.collection("orders").document(orderIdMutableLiveData.getValue());
            orderRef
                    .set(order)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(UberEatsRepository.class.getSimpleName(), "DocumentSnapshot successfully updated!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(UberEatsRepository.class.getSimpleName(), "Error updating document", e);
                        }
                    });
        }
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

    public MutableLiveData<List<MenuItem>> getMenuItemsMutableLiveData() {
        return menuItemsMutableLiveData;
    }

    public MutableLiveData<MenuItem> getSelectedMenuItemMutableLiveData() {
        return selectedMenuItemMutableLiveData;
    }

    public MutableLiveData<ShoppingCart> getShoppingCartMutableLiveData() {
        return shoppingCartMutableLiveData;
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<UberEatsAccount> getAccountLiveData() {
        return accountLiveData;
    }

    public MutableLiveData<List<AutocompletePrediction>> getPredictionMutableLiveData() {
        return predictionMutableLiveData;
    }

    public MutableLiveData<Order> getOrderMutableLiveData() {
        return orderMutableLiveData;
    }

    public MutableLiveData<String> getOrderIdMutableLiveData() {
        return orderIdMutableLiveData;
    }

    public static UberEatsRepository getInstance(){
        return instance;
    }
}
