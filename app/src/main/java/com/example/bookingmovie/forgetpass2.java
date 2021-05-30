package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class forgetpass2 extends AppCompatActivity {
    private TextInputEditText txt1;
    private TextInputEditText txt2;
    private TextInputEditText txt3;
    private Button btnConfirm;
    String ma;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{8,}" +                // at least 8 characters
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đổi mật khẩu");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        ma = preferences.getString(getString(R.string.confirm),"");
        txt1 =findViewById(R.id.txt_1);
        txt2 =findViewById(R.id.txt_2);
        txt3 =findViewById(R.id.txt_3);
        btnConfirm =findViewById(R.id.btn_change_pass);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatePassword()==true&&validateConfirm()==true&&validateConfirmPass()==true){
                    Toast.makeText(getApplicationContext(),"Đổi chưa biết",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"Đổi ko được biết",Toast.LENGTH_SHORT).show();
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
    public boolean validatePassword() {
        String passwordInput = txt1.getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (passwordInput.isEmpty()) {
            txt1.setError("Trường mật khẩu không được để trống");
            return false;
        }

        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            txt1.setError("Mật khẩu không đủ mạnh. Mật khẩu phải tối thiểu 8 kí tự, không kí tự trắng, và chứa các kí tự đăck biệt");
            return false;
        } else {
            txt1.setError(null);
            return true;
        }
    }
    public boolean validateConfirmPass(){
        String password = txt1.getText().toString();
        String password2 = txt2.getText().toString();
        if (password2.isEmpty()) {
            txt2.setError("Trường mật khẩu không được để trống");
            return false;
        }else
        if (password.equals(password2)==false){
            txt2.setError("Không trùng với mật khẩu mới");
            return false;
        }
        else
        {
            txt2.setError(null);
            return true;
        }
    }
    public boolean validateConfirm() {
        String maConfirm = txt3.getText().toString();
        if (maConfirm.isEmpty()){
            txt3.setError("Điền mã xác nhận đã gửi");
            return false;
        }else
            if (maConfirm.equals(ma)) {
                txt3.setError(null);
                return true;
            }
            else{
                txt3.setError("Mã xác nhận không hợp lệ");
                return true;}
    }
}