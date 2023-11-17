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
    public static final String TEN_BANG_WALLET = "Wallet";
    public static final String TEN_BANG_SAVINGS = "Savings";
    public static final String TEN_BANG_REPORT = "Report";
    public static final String TEN_BANG_CURRENCY = "Currency";


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
    public static final String COT_ID_USER_GD = "id_user_giaodich";

    //Cot bang danh muc
    public static final String COT_ID_DANHMUC ="id_danhmuc";
    public static final String COT_TENDANHMUC ="_tendanhmuc";
    public static final String COT_TYPE ="_type";


    //Cot bang vi
    public static final String COT_WALLET_ID = "_WalletID";
    public static final String COT_USERID ="_id_user";
    public static final String COT_BALANCE = "_balance";
    public static final String COT_WALLET_NAME = "_WalletName";
    public static final String COT_WALLET_CURRENCY_CODE ="WalletCurrencyCode";

    //Cot bảng sổ tiết kiệm
    public static final String COT_SAVINGS_ID = "_SavingsID";
    public static final String COT_UID = "_id_u";
    public static final String COT_TARGET_AMOUNT = "_TargetAmount";
    public static final String COT_CURRENT_AMOUNT ="_CurrentAmount";
    public static final String COT_START_DATE = "_StartDate";
    public static final String COT_END_DATE = "_EndDate";

    //Cot bảng báo cáo
    public static final String COT_REPORT_ID = "_id_report";
    public static final String COT_USID = "_id_user_r";
    public static final String COT_START_DATE_REPORT = "_StartDateReport";
    public static final String COT_END_DATE_REPORT = "_EndDateReport";
    public static final String COT_TOTAL_EXPENSIVE = "_TotalExpensive";
    public static final String COT_TOTAL_INCOME = "_TotalIncome";

    //Cot bảng currency
    public static final String COT_CURRENCY_ID = "_CurrencyID";
    public static final String COT_CURRENCY_CODE = "_CurrencyCode";
    public static final String COT_CURRENCY_NAME = "_CurrencyName";

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
            + COT_ID_DANHMUC + " integer, "
            + COT_ID_USER_GD + "integer, "
            + " foreign key (" + COT_ID_USER_GD + ") REFERENCES " + TEN_BANG_USER
            + "(" + COT_ID + "))";

    public static final String TAO_BANG_DANHMUC = ""
            + " create table " + TEN_BANG_DANHMUC + " ( "
            + COT_ID_DANHMUC + " integer primary key autoincrement, "
            + COT_TYPE + " text, "
            + COT_TENDANHMUC + " text );";

    public static final String TAO_BANG_WALLET = ""
            + " create table " + TEN_BANG_WALLET + " ( "
            + COT_WALLET_ID + " integer primary key autoincrement, "
            + COT_WALLET_NAME + " text, "
            + COT_BALANCE + " real, "
            + COT_CURRENCY_CODE + " text, "
            + COT_USERID + " integer, "
            + " foreign key (" + COT_USERID + ") REFERENCES " + TEN_BANG_USER
            + "(" + COT_ID + "))";

    public static final String TAO_BANG_REPORT = ""
            + " create table " + TEN_BANG_REPORT + " ( "
            + COT_REPORT_ID + " integer primary key autoincrement, "
            + COT_START_DATE_REPORT + " text, "
            + COT_END_DATE_REPORT + " text, "
            + COT_TOTAL_EXPENSIVE + " real, "
            + COT_TOTAL_INCOME + " real, "
            + COT_USID + " integer, "
            + " foreign key (" + COT_USID + ") REFERENCES " + TEN_BANG_USER
            + "(" + COT_ID + "))";

    public static final String TAO_BANG_SAVINGS = ""
            + " create table " + TEN_BANG_SAVINGS + " ( "
            + COT_SAVINGS_ID + " integer primary key autoincrement, "
            + COT_TARGET_AMOUNT + " real, "
            + COT_CURRENT_AMOUNT + " real, "
            + COT_START_DATE + " text, "
            + COT_END_DATE + " text, "
            + COT_UID + " integer, "
            + " foreign key (" + COT_UID + ") REFERENCES " + TEN_BANG_USER
            + "(" + COT_ID + "))";

    public static final String TAO_BANG_CURRENCY = ""
            + " create table " + TEN_BANG_CURRENCY + " ( "
            + COT_CURRENCY_ID + " integer primary key autoincrement, "
            + COT_CURRENCY_CODE + " text, "
            + COT_CURRENCY_NAME + " text );";

    public DBHelper( Context context) {
        super(context, TEN_DATABASE, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL(TAO_BANG_USER);
        MyDB.execSQL(TAO_BANG_GIAO_DICH);
        MyDB.execSQL(TAO_BANG_DANHMUC);
        MyDB.execSQL(TAO_BANG_WALLET);
        MyDB.execSQL(TAO_BANG_REPORT);
        MyDB.execSQL(TAO_BANG_SAVINGS);
        MyDB.execSQL(TAO_BANG_CURRENCY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS " + TEN_BANG_USER);
        MyDB.execSQL("DROP TABLE IF EXISTS " + TEN_BANG_GIAODICH);
        MyDB.execSQL("DROP TABLE IF EXISTS " + TEN_BANG_DANHMUC);
        MyDB.execSQL("DROP TABLE IF EXISTS " + TEN_BANG_CURRENCY);
        MyDB.execSQL("DROP TABLE IF EXISTS " + TEN_BANG_REPORT);
        MyDB.execSQL("DROP TABLE IF EXISTS " + TEN_BANG_WALLET);
        MyDB.execSQL("DROP TABLE IF EXISTS " + TEN_BANG_SAVINGS);
    }

}