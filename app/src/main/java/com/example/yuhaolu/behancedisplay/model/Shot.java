package com.example.yuhaolu.behancedisplay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Shot {
    @SerializedName("projects")
    @Expose
    public List<Project> projects = null;
}
