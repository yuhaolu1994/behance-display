package com.example.yuhaolu.behancedisplay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User_ {
    @SerializedName("display_name")
    @Expose
    public String displayName;

    @SerializedName("images")
    @Expose
    public Images images;

    @SerializedName("fields")
    @Expose
    public List<String> fields = null;
}
