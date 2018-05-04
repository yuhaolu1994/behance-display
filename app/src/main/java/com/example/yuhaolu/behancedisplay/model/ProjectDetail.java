package com.example.yuhaolu.behancedisplay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectDetail {
    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("covers")
    @Expose
    public Covers covers;

    @SerializedName("stats")
    @Expose
    public Stats_ stats;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("fields")
    @Expose
    public List<String> fields = null;

    @SerializedName("owners")
    @Expose
    public List<Owner> owners = null;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("url")
    @Expose
    public String url;
}
