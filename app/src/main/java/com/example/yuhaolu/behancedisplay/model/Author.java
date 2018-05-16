package com.example.yuhaolu.behancedisplay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Author {
    @SerializedName("users")
    @Expose
    public List<User_> users = null;
}
