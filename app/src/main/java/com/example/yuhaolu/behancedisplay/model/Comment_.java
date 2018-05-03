package com.example.yuhaolu.behancedisplay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment_ {
    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("comment")
    @Expose
    public String comment;

    @SerializedName("user")
    @Expose
    public User user;
}
