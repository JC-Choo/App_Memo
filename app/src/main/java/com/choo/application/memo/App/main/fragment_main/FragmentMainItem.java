package com.choo.application.memo.App.main.fragment_main;

/**
 * Created by Bridge on 2018-05-28.
 */

public class FragmentMainItem {
    private String content;
    private String time;
    private String etc;

    public FragmentMainItem(String content, String time, String etc) {
        this.content = content;
        this.time = time;
        this.etc = etc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }
}
