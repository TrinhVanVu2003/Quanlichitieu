//package com.example.appquanlichitieu.Add;
//
//import android.annotation.SuppressLint;
//import android.app.DatePickerDialog;
//import android.app.Dialog;
//import android.database.Cursor;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ListView;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
////import com.example.appquanlichitieu.Add.DanhMucAdapter;
//import com.example.appquanlichitieu.DBHelper;
//import com.example.appquanlichitieu.GiaoDich;
//import com.example.appquanlichitieu.GiaoDichAdapter;
//import com.example.appquanlichitieu.MyDatabase;
//import com.example.appquanlichitieu.R;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//public class KhoanThuFragment extends Fragment implements DatePickerDialog.OnDateSetListener  {
//    Spinner spnDanhMuc;
//    ImageButton btnimgAddDanhMuc;
//    View view;
//    Button btnThemDanhMuc, btnHuyThem,themKhoanThu;
//    EditText edtThemDanhMuc , edtGhiChu, edtKhoanthu;
//    public static MyDatabase database;
//    DBHelper dbHelper;
//    TextView mTvResultDate, tvDate;
//    private List<DanhMuc> danhMucList;
//    DanhMucAdapter danhMucAdapter;
//
//    public static ListView listView;
//    private static int id = -1;
//
//    public static ArrayList<GiaoDich> giaoDichs;
//
//    public KhoanThuFragment(){
//
//    }
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_khoan_thu, container, false);
//
//
//        //Ngay
//        mTvResultDate = view.findViewById(R.id.tvResultDate);
//        view.findViewById(R.id.imgbtnEditCalender).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ShowDatePickerDialog();
//            }
//        });
//
//        tvDate = view.findViewById(R.id.tvResultDate);
//        edtKhoanthu = view.findViewById(R.id.edtKhoanThu);
//        edtGhiChu = view.findViewById(R.id.edtGhiChuKT);
//        //Danh Mục
//        spnDanhMuc = view.findViewById(R.id.spnDanhMuc);
//        // Truy vấn dữ liệu danh mục từ bảng DanhMuc
//        // Khởi tạo cơ sở dữ liệu
//        MyDatabase database = new MyDatabase(getContext());
//// Lấy danh sách danh mục từ cơ sở dữ liệu
//        List<DanhMuc> danhMucList = database.getAllMaDanhMuc(getContext());
//// Tạo adapter
//        DanhMucAdapter danhMucAdapter = new DanhMucAdapter(getActivity(), R.layout.item_selected, danhMucList, getActivity());
//// Thiết lập adapter cho Spinner
//        spnDanhMuc.setAdapter(danhMucAdapter);
//
//        spnDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String selectedItem = adapterView.getItemAtPosition(i).toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        spnDanhMuc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                DanhMuc danhMuc = (DanhMuc) parent.getItemAtPosition(position);
//                danhMucList.remove(position);
//                spnDanhMuc.setSelection(0);
//                danhMucAdapter.notifyDataSetChanged();
//                return true;
//            }
//        });
//
//        btnimgAddDanhMuc = view.findViewById(R.id.imgbtnAddDanhMuc);
//        btnimgAddDanhMuc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openThemDanhMucDialog(Gravity.CENTER);
//
//            }
//        });
//
//        themKhoanThu = view.findViewById(R.id.btnThemKhoanThu);
//        // Thêm dòng này để khởi tạo giaoDichs rỗng
//        ArrayList<GiaoDich> giaoDichs = new ArrayList<>();
//
//// Khởi tạo GiaoDichAdapter
//        GiaoDichAdapter adapter = new GiaoDichAdapter(getActivity(), giaoDichs);
//
//// Thiết lập adapter cho listView
//        if (listView != null) {
//            listView.setAdapter(adapter);
//        }
//        ListView listView = view.findViewById(R.id.ListViewGiaoDich);
//
//        themKhoanThu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GiaoDich giaoDich1 = layDuLieuGiaoDich();
//                if (giaoDich1 != null) {
//                    if (database.ThemGiaoDich(giaoDich1) != -1) {
//                        giaoDichs.add(giaoDich1);
//                        capNhatDuLieu();
//                        if (listView != null) {
//                            listView.invalidateViews();
//                        }
//                        edtKhoanthu.setText("");
//                        edtGhiChu.setText("");
//                        tvDate.setText("");
//                        spnDanhMuc.setSelection(0); // chọn danh mục đầu tiên trong spinner
//                        id = -1;
//                    } else {
//                        // Hiển thị thông báo lỗi khi thêm giao dịch vào cơ sở dữ liệu không thành công
//                        Toast.makeText(getActivity(), "Thêm giao dịch không thành công", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    // Hiển thị thông báo khi người dùng chưa chọn danh mục trong spinner danh mục
//                    Toast.makeText(getActivity(), "Vui lòng chọn danh mục", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//        });
//        return view;
//    }
//
//
//
//    private void openThemDanhMucDialog(int gravity) {
//        final Dialog dialog = new Dialog(getActivity());
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.my_custom_dialog_themdanhmuc);
//
//        Window window = dialog.getWindow();
//        if (window == null) {
//            return;
//        }
//
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        //xử lí xác định vị trí của dialog
//        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.gravity = gravity;
//        window.setAttributes(windowAttributes);
//
//        edtThemDanhMuc = dialog.findViewById(R.id.edtThemDanhMuc);
//        btnThemDanhMuc = dialog.findViewById(R.id.btnThemdanhmuc);
//        btnHuyThem = dialog.findViewById(R.id.btnHuyThem);
//
//        //Thêm danh mục ở dialog
//        MyDatabase database = new MyDatabase(getContext());
//        List<DanhMuc> danhMucList = database.getAllMaDanhMuc(getContext());
//        DanhMucAdapter danhMucAdapter = new DanhMucAdapter(getActivity(), R.layout.item_danhmuc, danhMucList, getActivity());
//
//        btnThemDanhMuc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String danhmuc = edtThemDanhMuc.getText().toString();
//                if (danhmuc.trim().length()>0){
//                    MyDatabase db = new MyDatabase(getActivity());
//                    db.ThemDanhMuc(danhmuc);
//                    edtThemDanhMuc.setText("");
//                    danhMucList.clear();
//                    danhMucList.addAll(db.getAllMaDanhMuc(getActivity()));
//                    danhMucAdapter.notifyDataSetChanged();
//                } else {
//                    Toast.makeText(getActivity(), "vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//
//
//        btnHuyThem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }
//
//
//    private void ShowDatePickerDialog() {
//        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                getActivity(),
//                KhoanThuFragment.this,
//                Calendar.getInstance().get(Calendar.YEAR),
//                Calendar.getInstance().get(Calendar.MONTH),
//                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
//        );
//        datePickerDialog.show();
//    }
//
//
//    @Override
//    public void onDateSet(DatePicker view, int year, int dateOfmonth, int month) {
//        String date =  month + "/" + dateOfmonth + "/" + year;
//        mTvResultDate.setText(date);
//
//    }
//
//    @SuppressLint("Range")
//    public void capNhatDuLieu() {
//        if (giaoDichs == null) {
//            giaoDichs = new ArrayList<GiaoDich>();
//        } else {
//           giaoDichs.removeAll(giaoDichs);
//        }
//        if(database == null){
//            return;
//        }
//        //lấy dữ liệu dùng cursor nhận lại
//        Cursor cursor = database.LayDuLieuGiaoDich();
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                GiaoDich giaoDich = new GiaoDich();
//                giaoDich.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.COT_ID_GIAODICH))));
//                giaoDich.setAmount(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.COT_AMOUNT))));
//                giaoDich.setDate(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DATE)));
//                giaoDich.setCategory(cursor.getString(cursor.getColumnIndex(DBHelper.COT_CATEGORY)));
//                giaoDich.setGhiChu(cursor.getString(cursor.getColumnIndex(DBHelper.COT_GHICHU)));
//
//                giaoDichs.add(giaoDich);
//            }
//            cursor.close();
//        }
//        setListView();
//    }
//
//    public void setListView() {
//        if (giaoDichs != null) {
//            listView.setAdapter(new GiaoDichAdapter(getContext(), giaoDichs));
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                }
//            });
//        }
//    }
//
//    public GiaoDich layDuLieuGiaoDich(){
//        String date = tvDate.getText().toString();
//        String note = edtGhiChu.getText().toString();
//        String amountStr = edtKhoanthu.getText().toString();
//        DanhMuc selectedDanhMuc = (DanhMuc) spnDanhMuc.getSelectedItem();
//        String danhmuc = selectedDanhMuc.get_tendanhmuc();
//        if (date.trim().length()==0||
//                note.trim().length()==0||
//                amountStr.trim().length()==0||
//                danhmuc.trim().length()==0)
//            return null;
//        int amount = Integer.parseInt(amountStr);
//        GiaoDich giaoDich = new GiaoDich();
//        giaoDich.setId(id);
//        giaoDich.setGhiChu(note);
//        giaoDich.setDate(date);
//        giaoDich.setIsKhoanThu(1);
//        giaoDich.setCategory(danhmuc);
//        giaoDich.setAmount(amount);
//        return giaoDich;
//    }
//}
