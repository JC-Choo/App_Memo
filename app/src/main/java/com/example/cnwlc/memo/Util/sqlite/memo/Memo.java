package com.example.cnwlc.memo.Util.sqlite.memo;

/**
 * Created by Bridge on 2018-05-21.
 */

public class Memo {
    private int _no;
    private String folderName;
    private String date;
    private String content;
    private String imagePath;

    public int get_no() {
        return _no;
    }

    public void set_no(int _no) {
        this._no = _no;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
