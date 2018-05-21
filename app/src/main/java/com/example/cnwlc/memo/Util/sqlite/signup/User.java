package com.example.cnwlc.memo.Util.sqlite.signup;

/**
 * Created by Bridge on 2018-05-21.
 */

public class User {
    private int _no;
    private String id;
    private String password;
    private String phone;

    public int get_no() {
        return _no;
    }

    public void set_no(int _no) {
        this._no = _no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
