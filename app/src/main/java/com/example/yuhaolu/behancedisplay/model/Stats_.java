package com.example.yuhaolu.behancedisplay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats_ {
    @SerializedName("views")
    @Expose
    public Integer views;

    @SerializedName("appreciations")
    @Expose
    public Integer appreciations;

    @SerializedName("comments")
    @Expose
    public Integer comments;
}
