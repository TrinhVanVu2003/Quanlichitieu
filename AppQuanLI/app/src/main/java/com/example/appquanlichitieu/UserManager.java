package com.example.appquanlichitieu;

import android.content.Context;
import android.content.SharedPreferences;

public class UserManager {

    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_USER_ID = "UserID";

    private final SharedPreferences preferences;

    public UserManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setCurrentUserID(int userID) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_USER_ID, userID);
        editor.apply();
    }

    public int getCurrentUserID() {
        return preferences.getInt(KEY_USER_ID, -1); // -1 là giá trị mặc định nếu không tìm thấy
    }
}
