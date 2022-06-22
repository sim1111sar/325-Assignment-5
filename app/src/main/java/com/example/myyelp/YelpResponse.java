package com.example.myyelp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class YelpResponse {

    @SerializedName("id")
    public String mID;

    @SerializedName("name")
    public String mName;

    @SerializedName("image_url")
    public String mImage;

    @SerializedName("categories")
    public ArrayList<Categories> mCategories;

    class Categories{

        @SerializedName("title")
        public String mTitle;

    }

    @SerializedName("rating")
    public float mRating;

    @SerializedName("price")
    public String mPrice;

    @SerializedName("phone")
    public String mPhone;

    @SerializedName("location")
    public Location mLocation;

    class Location{

        @SerializedName("address1")
        public String mAddress;

        @SerializedName("city")
        public String mCity;

        @SerializedName("state")
        public String mState;

    }

}
