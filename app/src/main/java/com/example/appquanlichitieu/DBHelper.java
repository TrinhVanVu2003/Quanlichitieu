package com.example.appquanlichitieu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TEN_DATABASE= "QuanLiChiTieu.db";

    public static final String TEN_BANG_USER = "users";
    public static final String TEN_BANG_GIAODICH = "GiaoDich";
    public static final String TEN_BANG_DANHMUC = "DanhMuc";

    //Cot bang user
    public static final String COT_ID ="_id";
    public static final String COT_USERNAME ="_username";
    public static final String COT_PASSWORD ="_password";

    //Cot bang giao dich
    public static final String COT_ID_GIAODICH = "id_giaodich";
    public static final String COT_CATEGORY = "_category";
    public static final String COT_GHICHU = "_ghichu";
    public static final String COT_AMOUNT = "_amount";
    public static final String COT_ISKHOANTHU = "_iskhoanthu";
    public static final String COT_DATE = "_date";

    //Cot bang danh muc
    public static final String COT_ID_DANHMUC ="id_danhmuc";
    public static final String COT_TENDANHMUC ="_tendanhmuc";

    public static final String TAO_BANG_USER = ""
                 + "CREATE TABLE " + TEN_BANG_USER + " ( "
                 + COT_ID + " integer primary key autoincrement, "
                 + COT_USERNAME + " text not null, "
                 + COT_PASSWORD + " text not null );";

    public static final String TAO_BANG_GIAO_DICH = ""
            + " create table " + TEN_BANG_GIAODICH + " ( "
            + COT_ID_GIAODICH + " integer primary key autoincrement, "
            + COT_CATEGORY + " text not null, "
            + COT_AMOUNT + " real, "
            + COT_GHICHU + " text, "
            + COT_ISKHOANTHU + " integer, "
            + COT_DATE + " text, "
            + COT_ID_DANHMUC + " integer, " // Thêm cột liên kết với bảng danh mục
            + " foreign key (" + COT_ID_DANHMUC + ") REFERENCES " + TEN_BANG_DANHMUC
            + "(" + COT_ID_DANHMUC + "));";

    public static final String TAO_BANG_DANHMUC = ""
            + " create table " + TEN_BANG_DANHMUC + " ( "
            + COT_ID_DANHMUC + " integer primary key autoincrement, "
            + COT_TENDANHMUC + " text );";

    public DBHelper( Context context) {
        super(context, TEN_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL(TAO_BANG_USER);
        MyDB.execSQL(TAO_BANG_GIAO_DICH);
        MyDB.execSQL(TAO_BANG_DANHMUC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS " + TEN_BANG_USER);
        MyDB.execSQL("DROP TABLE IF EXISTS " + TEN_BANG_GIAODICH);
        MyDB.execSQL("DROP TABLE IF EXISTS " + TEN_BANG_DANHMUC);
    }

}