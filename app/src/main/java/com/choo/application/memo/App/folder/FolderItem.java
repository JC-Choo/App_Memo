package com.choo.application.memo.App.folder;

/**
 * Created by JCChu on 2018-06-15.
 */

public class FolderItem {

    private String stringTitle;
    private String stringCountOfMemos;
    private boolean isSelected;

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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
