package com.example.appquanlichitieu;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.appquanlichitieu.Add.DanhMuc;
import com.example.appquanlichitieu.Category.Category;
import com.example.appquanlichitieu.Transacion.Transacion;
import com.example.appquanlichitieu.Wallet.Wallet;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public double getWalletBalance() {
        double balance = 0.0;

        // Tạo câu truy vấn để lấy số dư của ví
        String query = "SELECT " + DBHelper.COT_BALANCE +
                " FROM " + DBHelper.TEN_BANG_WALLET ;

        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TEN_BANG_WALLET, null);


        // Kiểm tra xem có dữ liệu từ câu truy vấn không
        if (cursor.moveToFirst()) {
            balance = cursor.getDouble(cursor.getColumnIndex(DBHelper.COT_BALANCE));
        }

        // Đóng cursor và database để tránh leak
        cursor.close();
        database.close();

        return balance;
    }
    public double getTotalBalance() {
        double total = 0;

        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TEN_BANG_WALLET, null);

        while (cursor.moveToNext()) {
            String balanceStr = cursor.getString(cursor.getColumnIndex(DBHelper.COT_BALANCE));

            // Loại bỏ dấu "," khỏi chuỗi số tiền
            balanceStr = balanceStr.replace(",", "");

            double balance = Double.parseDouble(balanceStr);

            String currencyCode = cursor.getString(cursor.getColumnIndex(DBHelper.COT_WALLET_CURRENCY_CODE));

            if (!"VND".equals(currencyCode)) {
                double exchangeRate = getExchangeRate(currencyCode, "VND"); // Lấy tỷ giá chuyển đổi
                balance *= exchangeRate;
            }

            total += balance;
        }

        cursor.close();
        return total;
    }


    // chuyển đổi tỷ giá
    private double getExchangeRate(String fromCurrency, String toCurrency) {
        // giá theo google có thể tủy chỉnh tùy thích
        double usdToVndRate = 22000;
        double cnyToVndRate = 3300;

        // Kiểm tra từ đơn vị tiền tệ và đến đơn vị tiền tệ để xác định tỷ giá chuyển đổi
        if ("USD".equals(fromCurrency) && "VND".equals(toCurrency)) {
            return usdToVndRate;
        } else if ("CNY".equals(fromCurrency) && "VND".equals(toCurrency)) {
            return cnyToVndRate;
        } else if ("VND".equals(fromCurrency) && "USD".equals(toCurrency)) {
            return 1 / usdToVndRate;
        } else if ("VND".equals(fromCurrency) && "CNY".equals(toCurrency)) {
            return 1 / cnyToVndRate;
        } else {
            // Trong trường hợp không biết tỷ giá, trả về 1 (giữ nguyên số tiền)
            return 1;
        }
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
    private void updateWalletBalance(int walletID, double amount, boolean isIncome) {
        String operator = (isIncome) ? "+" : "-";
        String updateQuery = "UPDATE " + DBHelper.TEN_BANG_WALLET +
                " SET " + DBHelper.COT_BALANCE + " = " + DBHelper.COT_BALANCE + " " + operator + " " + amount +
                " WHERE " + DBHelper.COT_WALLET_ID + " = " + walletID;

        database.execSQL(updateQuery);
    }
    private void updateWalletBalanceChi(int walletID, double amount, boolean isIncome) {
        String operator =  "-";
        String updateQuery = "UPDATE " + DBHelper.TEN_BANG_WALLET +
                " SET " + DBHelper.COT_BALANCE + " = " + DBHelper.COT_BALANCE + " " + operator + " " + amount +
                " WHERE " + DBHelper.COT_WALLET_ID + " = " + walletID;

        database.execSQL(updateQuery);
    }
    // thêm giao dịch
    public void addTransacionToDatabase(Transacion transacion,String transactionType){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_ID_GIAODICH, transacion.getTransacionID());
        values.put(DBHelper.COT_AMOUNT, transacion.getAmount());
        values.put(DBHelper.COT_GHICHU, transacion.getNote());
        values.put(DBHelper.COT_DATE, transacion.getDate());
        values.put(DBHelper.COT_CATE_ID, transacion.getCateID());
        values.put(DBHelper.COT_WALLET_ID_TRANS, transacion.getTransWalletID());
        values.put(DBHelper.COT_ID_USER_GD, transacion.getUserID());
        values.put(DBHelper.COT_WALLET_NAMEE,transacion.getWalletName());
        database.insert(DBHelper.TEN_BANG_GIAODICH,null,values);
        if (transactionType.equals("Thu")) {
            updateWalletBalance(transacion.getTransWalletID(), transacion.getAmount(), true);
        } else {
            updateWalletBalanceChi(transacion.getTransWalletID(), transacion.getAmount(), false);
        }

        database.close();
    }
    public void addCategories(List<Category> categories) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        try {
            for (Category category : categories) {
                // Kiểm tra xem giá trị ID đã tồn tại trong cơ sở dữ liệu chưa
                if (!isCategoryIDExists(database, category.getCategoryID())) {
                    values.put(DBHelper.COT_ID_DANHMUC, category.getCategoryID());
                    values.put(DBHelper.COT_TENDANHMUC, category.getCategoryName());
                    values.put(DBHelper.COT_TYPE, category.getCategoryType());
                    values.put(DBHelper.COT_IMG_DANHMUC, category.getCategoryIMG());
                    database.insert(DBHelper.TEN_BANG_DANHMUC, null, values);
                } else {
                    // Nếu giá trị ID đã tồn tại, bạn có thể xử lý hoặc bỏ qua
                    Log.e("DuplicateCategoryID", "Category with ID " + category.getCategoryID() + " already exists.");
                }

                // Nếu cần, có thể log hoặc xử lý giá trị đã được thêm vào cơ sở dữ liệu
                Log.d("CategoryData", "Category ID: " + category.getCategoryID() +
                        ", Name: " + category.getCategoryName() +
                        ", IMG: " + category.getCategoryIMG() +
                        ", Type: " + category.getCategoryType());

                // Đặt giá trị về rỗng để chuẩn bị cho lần lặp tiếp theo
                values.clear();
            }
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    // Hàm kiểm tra xem giá trị ID đã tồn tại trong cơ sở dữ liệu chưa
    private boolean isCategoryIDExists(SQLiteDatabase database, int categoryID) {
        String query = "SELECT COUNT(*) FROM " + DBHelper.TEN_BANG_DANHMUC + " WHERE " + DBHelper.COT_ID_DANHMUC + " = " + categoryID;
        Cursor cursor = database.rawQuery(query, null);

        int count = 0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            cursor.close();
        }

        return count > 0;
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

        Cursor cursor = database.rawQuery("SELECT SUM(" + DBHelper.COT_AMOUNT + ") FROM " + DBHelper.TEN_BANG_GIAODICH , null);
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        return total;
    }

    public int getTotalKhoanChi() {
        int total = 0;
        Cursor cursor = database.rawQuery("SELECT SUM(" + DBHelper.COT_AMOUNT + ") FROM " + DBHelper.TEN_BANG_GIAODICH, null);
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
    // lấy danh sách giao dịch từ cơ sở dữ liệu
    public List<Transacion> getListTransactions() {
        List<Transacion> transactions = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT "
                + DBHelper.TEN_BANG_GIAODICH + "." + DBHelper.COT_ID_GIAODICH + ", "
                + DBHelper.TEN_BANG_GIAODICH + "." + DBHelper.COT_ID_USER_GD + ", "
                + DBHelper.TEN_BANG_GIAODICH + "." + DBHelper.COT_GHICHU + ", "
                + DBHelper.TEN_BANG_GIAODICH + "." + DBHelper.COT_WALLET_NAMEE + ", "
                + DBHelper.TEN_BANG_GIAODICH + "." + DBHelper.COT_AMOUNT + ", "
                + DBHelper.TEN_BANG_GIAODICH + "." + DBHelper.COT_DATE + ", "
                + DBHelper.TEN_BANG_DANHMUC + "." + DBHelper.COT_TENDANHMUC + " AS " + "categoryName, "
                + DBHelper.TEN_BANG_DANHMUC + "." + DBHelper.COT_ID_DANHMUC + " AS " + "categoryID, "
                + DBHelper.TEN_BANG_DANHMUC + "." + DBHelper.COT_TYPE + " AS " + "type, "
                + DBHelper.TEN_BANG_GIAODICH + "." + DBHelper.COT_WALLET_ID_TRANS + " AS " + "walletID"
                + " FROM " + DBHelper.TEN_BANG_GIAODICH
                + " LEFT JOIN " + DBHelper.TEN_BANG_DANHMUC
                + " ON " + DBHelper.TEN_BANG_GIAODICH + "." + DBHelper.COT_CATE_ID + " = " + DBHelper.TEN_BANG_DANHMUC + "." + DBHelper.COT_ID_DANHMUC;

        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                int transactionID = cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_GIAODICH));
                String note = cursor.getString(cursor.getColumnIndex(DBHelper.COT_GHICHU));
                String walletName = cursor.getString(cursor.getColumnIndex(DBHelper.COT_WALLET_NAMEE));
                double amount = cursor.getDouble(cursor.getColumnIndex(DBHelper.COT_AMOUNT));
                String date = cursor.getString(cursor.getColumnIndex(DBHelper.COT_DATE));
                String categoryName = cursor.getString(cursor.getColumnIndex("categoryName"));
                int categoryID = cursor.getInt(cursor.getColumnIndex("categoryID"));
                int walletID = cursor.getInt(cursor.getColumnIndex("walletID"));
                int UserID  = cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_USER_GD));
                String type = cursor.getString(cursor.getColumnIndex("type"));

                Transacion transaction = new Transacion(transactionID, date, amount, note, categoryName, type,walletName,categoryID, walletID,UserID);
                transactions.add(transaction);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return transactions;
    }


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
                Double balance = cursor.getDouble(cursor.getColumnIndex(DBHelper.COT_BALANCE));
                int userID = cursor.getInt(cursor.getColumnIndex(DBHelper.COT_USERID));

                Wallet wallet = new Wallet(walletID, walletName, currency, currencyCode, balance, userID);
                walletList.add(wallet);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return walletList;
    }
    public int getMaxTransactionId() {
        int maxId = 0;

        String query = "SELECT MAX(" + DBHelper.COT_ID_GIAODICH + ") FROM " + DBHelper.TEN_BANG_GIAODICH;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            maxId = cursor.getInt(0);
        }

        cursor.close();
        return maxId;
    }


}



