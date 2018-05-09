package com.example.yuhaolu.behancedisplay.model;

import java.util.ArrayList;

public class SectionDataModel {
    private String headerTitle;
    private ArrayList<Project> allItemInSection;

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<Project> getAllItemInSection() {
        return allItemInSection;
    }

    public void setAllItemInSection(ArrayList<Project> allItemInSection) {
        this.allItemInSection = allItemInSection;
    }
}
