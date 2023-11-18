package com.example.appquanlichitieu;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.appquanlichitieu.Add.DanhMuc;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase {
    SQLiteDatabase database;
    DBHelper dbHelper;

    public MyDatabase(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
//        database = dbHelper.getReadableDatabase();
    }

    public MyDatabase() {

    }


    // thêm ví
    public void addWalletToDatabase(Wallet wallet) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_WALLET_NAME, wallet.getWalletName());
        values.put(DBHelper.COT_CURRENCY_NAME, wallet.getWalletCurrency());
        values.put(DBHelper.COT_WALLET_CURRENCY_CODE, wallet.getCurrencyCode());
        values.put(DBHelper.COT_BALANCE, wallet.getBalance());
        values.put(DBHelper.COT_USERID, wallet.getUserID());

        database.insert(DBHelper.TEN_BANG_WALLET, null, values);
        database.close();
    }

    // thêm dữ liệu
    public int ThemDanhMuc(String tenDanhMuc) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(DBHelper.COT_TENDANHMUC, tenDanhMuc);
            long result = db.insert(DBHelper.TEN_BANG_DANHMUC, null, values);
            return result != -1 ? 1 : -1;
        } catch (Exception ex) {
            Log.e("MyDatabase", ex.getMessage());
            return -1;
        }
    }

    public List<DanhMuc> getAllMaDanhMuc(Context context) {
        List<DanhMuc> list = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(context);
        try (SQLiteDatabase db = dbHelper.getWritableDatabase();
             Cursor cursor = db.query(DBHelper.TEN_BANG_DANHMUC, null, null, null, null, null, null)) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COT_ID_DANHMUC));
                String tenDanhMuc = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COT_TENDANHMUC));
                DanhMuc danhMuc = new DanhMuc(id, tenDanhMuc);
                list.add(danhMuc);
            }
        } catch (Exception ex) {
            Log.e("MyDatabase", ex.getMessage());
        }
        return list;
    }

    @SuppressLint("Range")
    public List<GiaoDich> getAllDanhMucGiaoDich(Context context) {
        List<GiaoDich> list = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(context);
        try (SQLiteDatabase db = dbHelper.getWritableDatabase();
             Cursor cursor = db.query(DBHelper.TEN_BANG_GIAODICH, null, null, null, null, null, null)) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COT_ID_DANHMUC));
                GiaoDich giaoDich = new GiaoDich();
                giaoDich.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.COT_ID_GIAODICH))));
                giaoDich.setAmount((int) cursor.getDouble(cursor.getColumnIndex(DBHelper.COT_AMOUNT)));
                giaoDich.setIsKhoanThu(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.COT_ISKHOANTHU))));
                giaoDich.setDate(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DATE)));
                giaoDich.setCategory(cursor.getString(cursor.getColumnIndex(DBHelper.COT_CATEGORY)));
                giaoDich.setGhiChu(cursor.getString(cursor.getColumnIndex(DBHelper.COT_GHICHU)));


                list.add(giaoDich);
            }
        } catch (Exception ex) {
            Log.e("MyDatabase", ex.getMessage());
        }
        return list;
    }

    public Cursor LayDuLieuGiaoDich() {
        //biến cot là khai báo danh sách các cột cần lấy.
        String[] cot = {DBHelper.COT_ID_GIAODICH,
                DBHelper.COT_CATEGORY,
                DBHelper.COT_DATE,
                DBHelper.COT_ISKHOANTHU,
                DBHelper.COT_GHICHU,
                DBHelper.COT_AMOUNT};
        /*
         * Cursor như là 1 bảng cơ sở dữ liệu được trả ra
         * sau khi truy vấn trong cơ sở dữ liệu.
         */
        Cursor cursor = null;
        /*
         * Dùng lớp “SQLiteDatabase” truy xuất đến phương
         * thức “query” để lấy dữ liệu trong cơ sở dữ liệu ra.
         * Ở trong phương thức này là yêu cầu lấy tất cả nên
         * chỉ cần truyền vào các tham số như: tên bảng, các
         * cột cần lấy (cot) và sắp xếp nếu cần.
         */
        cursor = database.query(DBHelper.
                        TEN_BANG_GIAODICH, cot, null, null, null, null,
                DBHelper.COT_ID + " DESC");
        return cursor;
    }

    // Trong lớp MyDatabase
    public int getUserIDByUsername(String username) {
        int userID = -1;

        String[] columns = {DBHelper.COT_ID};
        String selection = DBHelper.COT_USERNAME + "=?";
        String[] selectionArgs = {username};

        Cursor cursor = database.query(DBHelper.TEN_BANG_USER, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            userID = cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID));
        }

        cursor.close();
        return userID;
    }

    public long ThemGiaoDich(GiaoDich giaoDich) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_amount", giaoDich.getAmount());
        contentValues.put("_iskhoanthu", giaoDich.getIsKhoanThu());
        contentValues.put("_ghichu", giaoDich.getGhiChu());
        contentValues.put("_category", giaoDich.getCategory());
        contentValues.put("_date", giaoDich.getDate());
        return database.insert(DBHelper.TEN_BANG_GIAODICH, null, contentValues);
    }

    public int getTotalKhoanThu() {
        int total = 0;

        Cursor cursor = database.rawQuery("SELECT SUM(" + DBHelper.COT_AMOUNT + ") FROM " + DBHelper.TEN_BANG_GIAODICH + " WHERE " + DBHelper.COT_ISKHOANTHU + " = 1", null);
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        return total;
    }

    public int getTotalKhoanChi() {
        int total = 0;
        Cursor cursor = database.rawQuery("SELECT SUM(" + DBHelper.COT_AMOUNT + ") FROM " + DBHelper.TEN_BANG_GIAODICH + " WHERE " + DBHelper.COT_ISKHOANTHU + " = 0", null);
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        return total;
    }


    // phương thức lấy dữ liệu user
    public boolean insertData(String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COT_USERNAME, username);
        contentValues.put(DBHelper.COT_PASSWORD, password);
        long result = database.insert(DBHelper.TEN_BANG_USER, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    @SuppressLint("Range")
    public String getUsername() {
        String result = "";
        Cursor cursor = database.rawQuery("SELECT " + DBHelper.COT_USERNAME + " FROM " + DBHelper.TEN_BANG_USER +
                " WHERE " + DBHelper.COT_USERNAME + " =?", null);
        if (cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndex(DBHelper.COT_USERNAME));
        }
        cursor.close();
        database.close();
        return result;
    }

    public boolean checkusername(String username) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TEN_BANG_USER + " WHERE " + DBHelper.COT_USERNAME + " = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkusernamepassword(String username, String password) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TEN_BANG_USER + " WHERE " + DBHelper.COT_USERNAME + " = ? AND " + DBHelper.COT_PASSWORD + " = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // Các phương thức khác

    // Lấy danh sách ví từ cơ sở dữ liệu
    public List<Wallet> getListWallet() {
        List<Wallet> walletList = new ArrayList<>();

        // Thực hiện truy vấn để lấy danh sách ví từ bảng Wallet trong cơ sở dữ liệu
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DBHelper.TEN_BANG_WALLET,
                null,
                null,
                null,
                null,
                null,
                null
        );

        // Xử lý dữ liệu từ Cursor và thêm vào danh sách
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int walletID = cursor.getInt(cursor.getColumnIndex(DBHelper.COT_WALLET_ID));
                String walletName = cursor.getString(cursor.getColumnIndex(DBHelper.COT_WALLET_NAME));
                String currency = cursor.getString(cursor.getColumnIndex(DBHelper.COT_CURRENCY_NAME));
                String currencyCode = cursor.getString(cursor.getColumnIndex(DBHelper.COT_WALLET_CURRENCY_CODE));
                String balance = cursor.getString(cursor.getColumnIndex(DBHelper.COT_BALANCE));
                int userID = cursor.getInt(cursor.getColumnIndex(DBHelper.COT_USERID));

                Wallet wallet = new Wallet(walletID, walletName, currency, currencyCode, balance, userID);
                walletList.add(wallet);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return walletList;
    }
}



