package com.example.yuhaolu.behancedisplay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Creatives {
    @SerializedName("creatives_to_follow")
    @Expose
    public List<CreativesToFollow> creativesToFollow = null;
}
