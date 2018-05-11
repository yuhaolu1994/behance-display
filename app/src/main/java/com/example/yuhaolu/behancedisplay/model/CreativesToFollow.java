package com.example.yuhaolu.behancedisplay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreativesToFollow {
    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("display_name")
    @Expose
    public String displayName;

    @SerializedName("images")
    @Expose
    public CreativeImages images;
}
