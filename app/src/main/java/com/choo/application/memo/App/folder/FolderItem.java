package com.choo.application.memo.App.folder;

/**
 * Created by JCChu on 2018-06-12.
 */

public class FolderItem {

    private String stringTitle;
    private String stringCountOfMemos;

    public FolderItem(String stringTitle, String stringCountOfMemos) {
        this.stringTitle = stringTitle;
        this.stringCountOfMemos = stringCountOfMemos;
    }

    public String getStringTitle() {
        return stringTitle;
    }

    public void setStringTitle(String stringTitle) {
        this.stringTitle = stringTitle;
    }

    public String getStringCountOfMemos() {
        return stringCountOfMemos;
    }

    public void setStringCountOfMemos(String stringCountOfMemos) {
        this.stringCountOfMemos = stringCountOfMemos;
    }
}
