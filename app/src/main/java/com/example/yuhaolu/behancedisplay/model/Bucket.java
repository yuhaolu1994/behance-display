package com.example.yuhaolu.behancedisplay.model;

import java.util.List;

public class Bucket {
    public String bucketName;
    public String bucketDescription;
    public int shotNum;
    public boolean isChoosing;
    public List<ProjectDetail> bucketProjects;

    public String getBucketName() {
        return bucketName;
    }

}
