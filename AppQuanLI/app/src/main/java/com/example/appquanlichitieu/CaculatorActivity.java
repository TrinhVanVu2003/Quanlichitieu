package com.example.appquanlichitieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Script;

import java.text.DecimalFormat;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CaculatorActivity extends AppCompatActivity {
    TextView tvWorking ;
    TextView tvResult;
    ImageView imgDone;
    String workings = "";
    String formattedResult = ""; // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caculator);
        initTextViews();

        imgDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("calculatedResult", formattedResult);
                setResult(RESULT_OK, intent);
                // Kết thúc CaculatorActivity
                finish();
            }
        });
    }
    private void  initTextViews(){
        tvWorking = findViewById(R.id.tvCaculatorWorking);
        tvResult = findViewById(R.id.tvCaculatorResult);
        imgDone = findViewById(R.id.imgDone);
    }

    private void setTvWorking(String givenValues){

        workings = workings +givenValues;
        tvWorking.setText(workings);
    }
    public void equalOnclick(View view) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###");

        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

        try {
            result = (double)engine.eval(workings);
        } catch (ScriptException e) {
            Toast.makeText(this, "Giá trị nhập không hợp lệ", Toast.LENGTH_SHORT).show();
        }
        if (result != null){
            tvResult.setText(String.valueOf(result.doubleValue()));
        }
        if (result != null) {
            formattedResult = decimalFormat.format(result);
            tvResult.setText(formattedResult);
        }
    }

    public void clearOnclick(View view) {
        tvWorking.setText("");
        workings = "";
        tvResult.setText("");
    }

    public void divideOnclick(View view) {
        setTvWorking("/");

    }

    public void multiOnclick(View view) {
        setTvWorking("*");

    }

    private String removeLastCharacter(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public void backspaceOnclick(View view) {
        workings = removeLastCharacter(workings);
        tvWorking.setText(workings);
    }

    public void oneOnclick(View view) {
        setTvWorking("1");

    }

    public void twoOnclick(View view) {
        setTvWorking("2");

    }

    public void threeOnclick(View view) {
        setTvWorking("3");

    }

    public void fourOnclick(View view) {
        setTvWorking("4");

    }

    public void fiveOnclick(View view) {
        setTvWorking("5");

    }

    public void sixOnclick(View view) {
        setTvWorking("6");

    }

    public void sevenOnclick(View view) {
        setTvWorking("7");

    }

    public void eighOnclick(View view) {
        setTvWorking("8");

    }

    public void nineOnclick(View view) {
        setTvWorking("9");

    }

    public void SubtractionOnclick(View view) {
        setTvWorking("-");

    }

    public void zzzOnclick(View view) {
        setTvWorking("000");

    }

    public void dotOnclick(View view) {
        setTvWorking(".");

    }


    public void addOnclick(View view) {
        setTvWorking("+");
    }
}