package com.example.cnwlc.memo.App.main;

/**
 * Created by Bridge on 2018-05-16.
 */

public class MainItem {
    private String title;
    private String daily;
    private String importance;

    public MainItem(String title, String daily, String importance) {
        this.title = title;
        this.daily = daily;
        this.importance = importance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDaily() {
        return daily;
    }

    public void setDaily(String daily) {
        this.daily = daily;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }
}
