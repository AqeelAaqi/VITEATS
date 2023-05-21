package com.etechspare.viteats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Restaurant {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("restaurant_name")
    @Expose
    public String restaurantName;
    @SerializedName("cuisine_type")
    @Expose
    public String cuisineType;
    @SerializedName("address")
    @Expose
    public Address address;
    @SerializedName("ratings")
    @Expose
    public List<Rating> ratings;
    @SerializedName("image_url")
    @Expose
    public String imageUrl;
}
