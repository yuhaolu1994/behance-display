package com.example.yuhaolu.behancedisplay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Collection_ {
    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("stats")
    @Expose
    public Stats___ stats;

    @SerializedName("project_covers")
    @Expose
    public List<ProjectCover> projectCovers;
}
