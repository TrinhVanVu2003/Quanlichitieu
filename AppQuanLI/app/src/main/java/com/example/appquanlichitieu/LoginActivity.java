package com.example.appquanlichitieu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView tvDangki;
    CheckBox mCheckboxRememberMe;
    MyDatabase myDatabase;

    // Key để lưu thông tin đăng nhập trong SharedPreferences
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";
    private static final String PREF_REMEMBER_ME = "rememberMe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvDangki = findViewById(R.id.tvDangki);
        myDatabase = new MyDatabase(this);
        mCheckboxRememberMe = findViewById(R.id.chkRememberMe);

        // Kiểm tra xem đã lưu thông tin đăng nhập từ trước hay chưa
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean rememberMe = prefs.getBoolean(PREF_REMEMBER_ME, false);
        if (rememberMe) {
            String savedUsername = prefs.getString(PREF_USERNAME, "");
            String savedPassword = prefs.getString(PREF_PASSWORD, "");

            // Hiển thị thông tin đăng nhập đã lưu trước đó
            edtUsername.setText(savedUsername);
            edtPassword.setText(savedPassword);
            mCheckboxRememberMe.setChecked(true);
        }

        tvDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        // Đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                if (username.isEmpty() && password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuserpass = myDatabase.checkusernamepassword(username, password);
                    if (checkuserpass) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                        // Lưu thông tin đăng nhập nếu người dùng đã chọn Remember Me
                        if (mCheckboxRememberMe.isChecked()) {
                            SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                            editor.putString(PREF_USERNAME, username);
                            editor.putString(PREF_PASSWORD, password);
                            editor.putBoolean(PREF_REMEMBER_ME, true);
                            editor.apply();
                            int userID = myDatabase.getUserIDByUsername(username);
                            UserManager userManager = new UserManager(LoginActivity.this);
                            userManager.setCurrentUserID(userID);

                        }

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Tên tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

   // public  void rememberUser(String usename , String pass , boolean status){
      //  SharedPreferences sharedPreferences = getSharedPreferences("prefname", Context.MODE_PRIVATE);
      //  SharedPreferences.Editor editor = sharedPreferences.edit();
      //  if(!status){
        //    editor.clear();
        //}else {
            //them data vao file
          //  editor.putString("USERNAME",usename);
          //  editor.putString("PASSWORD",pass);
          //  editor.putBoolean("REMEMBER",status);
       // }
        //luu lai
       // editor.commit();
    //}
  //  private void restoringPreference(){
        //SharedPreferences sharedPreferences = this.getSharedPreferences("prefname",Context.MODE_PRIVATE);
        //if(sharedPreferences != null){
            //lấy giá trị checked ra , nếu không lấy thì mặc định là false
           // boolean status = sharedPreferences.getBoolean("checked",false);
           // if(status){
               // String user = sharedPreferences.getString("USERNAME","admin");
               // String pass = sharedPreferences.getString("PASSWORD","admin123");
               // edtUsername.setText(user);
             //   edtPassword.setText(pass);
          //  }
           // mCheckboxRememberMe.setChecked(status);
      //  }
   // }
