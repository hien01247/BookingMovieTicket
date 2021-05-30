package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookingmovie.Database.DatabaseUser;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{8,}" +                // at least 8 characters
                    "$");
    private TextInputEditText password;
    private TextInputEditText hoten;
    private TextInputEditText email;
    private TextInputEditText sodienthoai;
    private  Button buttonReg;
    DatabaseUser DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        password = findViewById(R.id.txt_Reg_Pass);
        hoten = findViewById(R.id.txt_Reg_Name);
        email = findViewById(R.id.txt_Reg_Email);
        sodienthoai = findViewById(R.id.txt_Reg_SDT);
        buttonReg = findViewById(R.id.btn_reg_submit);
        DB = new DatabaseUser(Registration.this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng kí tài khoản");
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmInput(v);
                String pass = password.getText().toString();
                String phone = sodienthoai.getText().toString();
                String mail = email.getText().toString();
                String name = hoten.getText().toString();

                if (validateEmail() == true && validateName() == true &&  validatePhone() == true && validatePassword() == true){
                    Boolean checkuser = DB.checkUsername(mail);
                    if (checkuser == false){
                        Boolean insert = DB.insertData(mail,pass,phone,name);
                        if (insert==true){
                            AlertDialog.Builder b = new AlertDialog.Builder(Registration.this);
                            b.setTitle("Thông báo");
                            b.setMessage("Đăng kí tài khoản mới thành công!!!");
                            b.setPositiveButton("Kết thúc!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(Registration.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                            AlertDialog al = b.create();
                            al.show();
                        }
                    }
                    else {
                        Toast.makeText(Registration.this,"Email đăng kí đã trùng. Vui lòng Email mới",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean validateEmail() {


        String emailInput = email.getText().toString().trim();

        // if the email input field is empty
        if (emailInput.isEmpty()) {
            email.setError("Trường email không được để trống");
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Vui lòng điền đúng email");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    public boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (passwordInput.isEmpty()) {
            password.setError("Trường mật khẩu không được để trống");
            return false;
        }

        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Mật khẩu không đủ mạnh. Mật khẩu phải tối thiểu 8 kí tự, không kí tự trắng, và chứa các kí tự đăck biệt");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
    public boolean validateName() {
        String nameInput = hoten.getText().toString();
        if (nameInput.isEmpty()){
            hoten.setError("Thiếu thông tin họ tên");
            return false;
        }
        return true;
    }
    public boolean validatePhone() {
        String nameInput = sodienthoai.getText().toString();
        if (nameInput.isEmpty()){
            sodienthoai.setError("Thiếu thông tin số điện thoại");
            return false;
        }
        return true;
    }
    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword() | !validateName() | !validatePhone()) {
            return;
        }
        // if the email and password matches, a toast message
        // with email and password is displayed
        /*String input = "Đăng kí thành công";
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();*/
    }
}