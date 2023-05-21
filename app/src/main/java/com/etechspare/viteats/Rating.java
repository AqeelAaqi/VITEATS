package com.etechspare.viteats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("rating")
    @Expose
    public Integer rating;
    @SerializedName("comment")
    @Expose
    public String comment;
}
