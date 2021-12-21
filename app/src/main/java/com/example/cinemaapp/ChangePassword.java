package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cinemaapp.Database.BookingCinemaDatabase;
import com.example.cinemaapp.model.TaiKhoan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ChangePassword extends AppCompatActivity {
    private TextInputEditText txtmatkhau;
    private TextInputEditText txtmatkhaumoi;
    private TextInputEditText txtmatkhaumoi1;

    private FirebaseAuth mAuth;
    //BookingCinemaDatabase DB;
    //private TaiKhoan taiKhoan;
    private Button changepass;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{8,}" +                // at least 8 characters
                    "$");                    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đổi mật khẩu");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);



        SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","username");

        mAuth = FirebaseAuth.getInstance();

        txtmatkhau = findViewById(R.id.txt_Acc_pass);
        txtmatkhaumoi = findViewById(R.id.txt_Acc_pass_new);
        txtmatkhaumoi1 = findViewById(R.id.txt_Acc_pass_new_cf);

        changepass = findViewById(R.id.btn_Acc_change);

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentPassword = txtmatkhau.getText().toString();

                String newPassword = txtmatkhaumoi.getText().toString();
                String confirmNewPassword = txtmatkhaumoi1.getText().toString();

                if (currentPassword.isEmpty()) {

                    txtmatkhau.setError("Password không được để trống");
                    txtmatkhau.requestFocus();
                    return;

                }
                else if (!PASSWORD_PATTERN.matcher(newPassword).matches()) {

                    txtmatkhaumoi.setError("Mật khẩu không đủ mạnh. Mật khẩu phải tối thiểu 8 kí tự, không kí tự trắng, và chứa các kí tự đặc biệt");
                    txtmatkhaumoi.requestFocus();
                    return;
                }
                else if (newPassword.isEmpty()) {

                    txtmatkhaumoi.setError("Password không được để trống");
                    txtmatkhaumoi.requestFocus();
                    return;

                }
                else if (confirmNewPassword.isEmpty()) {

                    txtmatkhaumoi1.setError("Password không được để trống");
                    txtmatkhaumoi1.requestFocus();
                    return;

                }
                else if (!confirmNewPassword.equals(newPassword)) {

                    txtmatkhaumoi1.setError("Password không khớp");
                    txtmatkhaumoi1.requestFocus();
                    return;

                }
                else {
                    // Get credentials of the actual user
                    AuthCredential credential = EmailAuthProvider.getCredential(mAuth.getCurrentUser().getEmail(), currentPassword);

                    mAuth.getCurrentUser().reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        // Update the password
                                        mAuth.getCurrentUser().updatePassword(newPassword)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {

                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if (task.isSuccessful()) {

                                                            AlertDialog.Builder b = new AlertDialog.Builder(ChangePassword.this);
                                                            b.setTitle("Thông báo");
                                                            b.setMessage("Đổi mật khẩu thành công!!!");
                                                            b.setPositiveButton("Kết thúc!", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id) {
                                                                    Intent intent = new Intent(ChangePassword.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            });
                                                            AlertDialog al = b.create();
                                                            al.show();
                                                        }

                                                        else {

                                                            Toast.makeText(ChangePassword.this, "Cập nhật bị lỗi", Toast.LENGTH_LONG).show();

                                                        }

                                                    }

                                                });
                                    }

                                    else {

                                        Toast.makeText(ChangePassword.this, "Mật khẩu bạn nhập vào không đúng! Xin hãy nhập lại!", Toast.LENGTH_LONG).show();

                                    }

                                }

                            });
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

}