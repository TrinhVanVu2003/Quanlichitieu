package com.example.appquanlichitieu.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquanlichitieu.CaculatorActivity;
import com.example.appquanlichitieu.Category.ChoseCategoryActivity;
import com.example.appquanlichitieu.MyDatabase;
import com.example.appquanlichitieu.R;
import com.example.appquanlichitieu.Transacion.Transacion;
import com.example.appquanlichitieu.UserManager;
import com.example.appquanlichitieu.Wallet.AddWalletActivity;
import com.example.appquanlichitieu.Wallet.Wallet;
import com.example.appquanlichitieu.Wallet.WalletActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;


public class AddFragment extends Fragment implements DatePickerDialog.OnDateSetListener{
    TextView tvChonCate,tvBalanceTranc , tvChonWallet, tvChonTime , tvNote, tvBalance;
    View mView;
    Button btnThemGiaoDich;
    private static int id = 0;

    private UserManager userManager;
    private String transactionType; // Thêm biến để lưu loại giao dịch

    private static final int REQUEST_CODE_CALCULATOR = 1;
    private static final int REQUEST_CODE_SELECT_CATEGORY = 2;
    private static final int REQUEST_CODE_WALLET = 3;
    private int selectedWalletID = -1; // Biến để lưu trữ WalletID
    private int selectedCategoryID = -1; // Biến để lưu trữ WalletID
    private String amountStr;  // Đặt biến amountStr ở cấp lớp

    public AddFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_add, container, false);

        tvBalance = mView.findViewById(R.id.tvWalletBalance);
        tvNote = mView.findViewById(R.id.tvNoteTranc);
        // sang trang chọn số dư
        tvBalanceTranc = mView.findViewById(R.id.tvBalanceAddTranc);
        tvBalanceTranc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaculatorActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CALCULATOR);

            }
        });

        // sang trang chọn danh mục
        tvChonCate = mView.findViewById(R.id.tvChonCate);
        tvChonCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChoseCategoryActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT_CATEGORY);
            }
        });

        // sang trang chọn wallet
        tvChonWallet = mView.findViewById(R.id.tvChonWallet);
        tvChonWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WalletActivity.class);
                startActivityForResult(intent, REQUEST_CODE_WALLET);
            }
        });

        //hiển thị chọn thời gian
        tvChonTime = mView.findViewById(R.id.tvChoseTime);
        tvChonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDatePickerDialog();
            }
        });

        return mView;
    }
    //xử lí kết quả của Category
    private void handleCategoryActivityResult(Intent data) {
        if (data != null) {

            String categoryType = data.getStringExtra("categoryType");
            String CateName = data.getStringExtra("IncomeName");
            int incomeImg = data.getIntExtra("IncomeIMG", 0);
            int categoryID = data.getIntExtra("selectedCategoryID",0);
            selectedCategoryID = categoryID;

            int color;
            if ("Khoản thu".equals(categoryType)) {
                transactionType = "Thu";
                color = getResources().getColor(R.color.xanh);
            } else if ("Khoản chi".equals(categoryType)) {
                transactionType = "Chi";
                color = getResources().getColor(R.color.RED);
            } else {
                // Màu mặc định nếu không xác định được loại
                color = getResources().getColor(R.color.black);
            }
            // áp dụng màu
            tvBalanceTranc.setTextColor(color);

            TextView tvChonCate = mView.findViewById(R.id.tvChonCate);
            ImageView imgCate = mView.findViewById(R.id.imgCate);
            tvChonCate.setText(CateName);
            imgCate.setImageResource(incomeImg);

        }
    }
    //xử lí kết quả của Calculator
    private void handleCalculatorActivityResult(Intent data) {
        if (data != null) {
            String calculatedResult = data.getStringExtra("calculatedResult");

            TextView tvBalancee = mView.findViewById(R.id.tvBalanceAddTranc);
            tvBalancee.setText(calculatedResult);
        }
    }
    // xử lí kết quả của wallet
    private void handleWalletActivityResult(Intent data) {
        if (data != null) {


            // Nhận WalletID từ Intent
            int walletID = data.getIntExtra("walletID", -1);

            // Lưu WalletID vào biến selectedWalletID
            selectedWalletID = walletID;



            // Hiển thị tên ví
            String WalletResult = data.getStringExtra("walletName");
            TextView tvChonVi = mView.findViewById(R.id.tvChonWallet);
            tvChonVi.setText(WalletResult);
        }

        // xử lí khi ấn vào thêm giao dịch
        btnThemGiaoDich = mView.findViewById(R.id.btnAddTrans);
        btnThemGiaoDich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areAllFieldsFilled()) {
                    Transacion transacion = getDataFromAddTrans();

                    MyDatabase database = new MyDatabase(getActivity());
                    database.addTransacionToDatabase(transacion,transactionType);
                    tvBalanceTranc.setText("");
                    tvChonCate.setText("");
                    tvChonWallet.setText("");
                    tvNote.setText("");
                    tvChonTime.setText("");
                    Toast.makeText(getActivity(),"Thêm giao dịch thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),"Điền đầy đủ thông tin đi bro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CALCULATOR && resultCode ==   CaculatorActivity.RESULT_OK) {
            handleCalculatorActivityResult(data);
        }else if (requestCode == REQUEST_CODE_SELECT_CATEGORY) {
            handleCategoryActivityResult(data);
        }
        else if (requestCode == REQUEST_CODE_WALLET) {
            handleWalletActivityResult(data);
        }
    }

    private void ShowDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                AddFragment.this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Tháng trong DatePickerDialog được đánh số từ 0 đến 11, không cần cộng thêm 1
        String date = (month + 1) + "/" + dayOfMonth + "/" + year;
        tvChonTime.setText(date);
    }


    private Transacion getDataFromAddTrans() {
        MyDatabase database = new MyDatabase(getActivity());
        int maxId = database.getMaxTransactionId();
        int newId = maxId + 1;

        // Lấy giá trị từ TextView
        String amountStr = tvBalanceTranc.getText().toString().trim();

        // Kiểm tra xem amountStr có phải là null hay không
        if (amountStr != null && !amountStr.isEmpty()) {
            double amount = parseAmountString(amountStr);

            String category = tvChonCate.getText().toString().trim();
            String note = tvNote.getText().toString().trim();
            String date = tvChonTime.getText().toString().trim();
            String walletName = tvChonWallet.getText().toString().trim();

            userManager = new UserManager(getActivity());
            int userID = userManager.getCurrentUserID();

            return new Transacion(newId, date, amount, note, category, transactionType, walletName, selectedWalletID, selectedCategoryID, userID);
        } else {
            // Xử lý trường hợp amountStr là null hoặc rỗng
            Toast.makeText(getActivity(), "Amount is null or empty", Toast.LENGTH_SHORT).show();
            return null; // hoặc trả về giá trị mặc định khác
        }
    }


    private boolean areAllFieldsFilled() {
        String amount = tvBalanceTranc.getText().toString().trim();
        String cate = tvChonCate.getText().toString().trim();
        String date = tvChonTime.getText().toString().trim();
        String wallet = tvChonWallet.getText().toString().trim();

        return !amount.isEmpty() && !cate.isEmpty() && !date.isEmpty()&&!wallet.isEmpty() ;
    }

    private double parseAmountString(String amountStr) {
        try {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
            Number number = numberFormat.parse(amountStr);
            return number.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

}