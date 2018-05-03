package com.example.yuhaolu.behancedisplay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShotDetail {
    @SerializedName("project")
    @Expose
    public ProjectDetail projectDetail;
}
