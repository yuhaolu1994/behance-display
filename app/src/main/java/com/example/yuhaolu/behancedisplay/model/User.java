package com.example.yuhaolu.behancedisplay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("display_name")
    @Expose
    public String displayName;

    @SerializedName("images")
    @Expose
    public Images images;
}
