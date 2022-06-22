package com.example.myyelp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BodyResponse {

    @SerializedName("businesses")
    public ArrayList<YelpResponse> mBusinesses;

}
