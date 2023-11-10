package com.example.appquanlichitieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.invoke.MethodType;

public class SignUpActivity extends AppCompatActivity {
    EditText edtUsername , edtPassword , edtRePassword;
    Button btnSignUp;
    MyDatabase myDatabase;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtUsername = findViewById(R.id.edtUsername1);
        edtPassword = findViewById(R.id.edtPassword1);
        edtRePassword= findViewById(R.id.edtRePassword);
        tvLogin = findViewById(R.id.TextviewLogin);
        myDatabase = new MyDatabase(this);
        btnSignUp = findViewById(R.id.btnSignup1);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUsername.getText().toString();
                String pass = edtPassword.getText().toString();
                String repass = edtRePassword.getText().toString();

                // Kiểm tra tên đăng nhập
                if(user.length() < 6 || user.contains(" ")) {
                    Toast.makeText(SignUpActivity.this, "Tên đăng nhập phải có ít nhất 6 kí tự và không chứa khoảng trắng.", Toast.LENGTH_SHORT).show();
                } else {
                    // Kiểm tra mật khẩu
                    if(!isValidPassword(pass)) {
                        Toast.makeText(SignUpActivity.this, "Mật khẩu phải từ 8 đến 20 kí tự và không chứa kí tự đặc biệt.", Toast.LENGTH_SHORT).show();
                    } else {
                        // Tiếp tục xử lý đăng ký nếu tên đăng nhập và mật khẩu hợp lệ

                        if(pass.equals(repass)) {
                            Boolean checkuser = myDatabase.checkusername(user);
                            if(!checkuser) {
                                Boolean insert = myDatabase.insertData(user, pass);
                                if(insert) {
                                    Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(SignUpActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(SignUpActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUpActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
    // Hàm kiểm tra mật khẩu
    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.length() <= 20 && !password.contains(" ") && !password.matches(".*[^a-zA-Z0-9].*");
    }

}