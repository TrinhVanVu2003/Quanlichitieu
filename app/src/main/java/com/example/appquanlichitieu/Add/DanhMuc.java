package com.example.appquanlichitieu.Add;

public class DanhMuc {
    private int _id;
    private String _tendanhmuc;

    public DanhMuc(int _id, String _tendanhmuc) {
        this._id = _id;
        this._tendanhmuc = _tendanhmuc;
    }

    public DanhMuc() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_tendanhmuc() {
        return _tendanhmuc;
    }

    public void set_tendanhmuc(String _tendanhmuc) {
        this._tendanhmuc = _tendanhmuc;
    }
}
