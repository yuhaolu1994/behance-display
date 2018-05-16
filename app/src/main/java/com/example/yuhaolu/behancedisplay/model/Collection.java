package com.example.yuhaolu.behancedisplay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Collection {
    @SerializedName("collections")
    @Expose
    public List<Collection_> collections = null;
}
