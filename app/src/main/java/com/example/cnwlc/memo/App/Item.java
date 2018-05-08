package com.example.cnwlc.memo.App;

/**
 * Created by Bridge on 2018-05-06.
 */


public class Item {
    private String sTitle;
    private String sDaily;
    private String sImport;

    public Item(String sTitle, String sDaily, String sImport) {
        this.sTitle = sTitle;
        this.sDaily = sDaily;
        this.sImport = sImport;
    }

    public String getsTitle() {
        return sTitle;
    }

    public String getsDaily() {
        return sDaily;
    }

    public String getsImport() {
        return sImport;
    }
}
