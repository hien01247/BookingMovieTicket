package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cinemaapp.model.TaiKhoan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{8,}" +                // at least 8 characters
                    "$");                    //

    private TextInputEditText hoten;
    private TextInputEditText email;
    private TextInputEditText sodienthoai;
    private TextInputEditText password;
    private TextInputEditText passwordCF;
    private  Button buttonReg;
    ProgressBar progressBar_SignUp;
    private static final String TAG =    MainActivity.class.getSimpleName();


    private FirebaseAuth mAuth;
    //BookingCinemaDatabase DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        hoten = findViewById(R.id.txt_Reg_Name);
        email = findViewById(R.id.txt_Reg_Email);
        sodienthoai = findViewById(R.id.txt_Reg_SDT);
        //username = findViewById(R.id.txt_Username);
        password = findViewById(R.id.txt_Reg_Pass);
        passwordCF = findViewById(R.id.txt_Reg_Pass_Cof);

        buttonReg = findViewById(R.id.btn_capnhap);
        progressBar_SignUp = findViewById(R.id.progressBar_SignUp);
        mAuth = FirebaseAuth.getInstance();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("????ng k?? t??i kho???n");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);


        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = hoten.getText().toString();
                String phone = sodienthoai.getText().toString();

                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String confirmPassword = passwordCF.getText().toString();


                // Validation of the fields

                if (name.isEmpty()) {

                    hoten.setError("T??n kh??ng ???????c ????? tr???ng");
                    hoten.requestFocus();
                    return;

                }

                else if (phone.isEmpty()) {

                    sodienthoai.setError("??i???n tho???i kh??ng ???????c ????? tr???ng");
                    sodienthoai.requestFocus();
                    return;

                }

                else if (mail.isEmpty()) {

                    email.setError("Email kh??ng ???????c ????? tr???ng");
                    email.requestFocus();
                    return;

                }

                else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {

                    email.setError("Email kh??ng ????ng! Xin ki???m tra l???i");
                    email.requestFocus();
                    return;

                }

                else if (pass.isEmpty()) {

                    password.setError("Password kh??ng ???????c ????? tr???ng");
                    password.requestFocus();
                    return;

                }
                else if (!PASSWORD_PATTERN.matcher(pass).matches()) {

                    password.setError("M???t kh???u kh??ng ????? m???nh. M???t kh???u ph???i t???i thi???u 8 k?? t???, kh??ng k?? t??? tr???ng, v?? ch???a c??c k?? t??? ?????c bi???t");
                    password.requestFocus();
                    return;
                }
                else if (confirmPassword.isEmpty()) {

                    password.setError("Password kh??ng ???????c ????? tr???ng");
                    password.requestFocus();
                    return;

                }
                else if (!confirmPassword.equals(pass)) {

                    passwordCF.setError("Password kh??ng kh???p");
                    passwordCF.requestFocus();
                    return;

                }
                else if (phone.length()<10 && phone.length()>11){

                    sodienthoai.setError("S??? ??i???n tho???i kh??ng ????ng");
                    sodienthoai.requestFocus();
                    return;

                }

                else {

                    mAuth.createUserWithEmailAndPassword(mail, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() { //Check if the registration is false or not and return back the correct answer

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {        // Check if the registration is successful

                                    if (task.isSuccessful()) {

                                        progressBar_SignUp.setVisibility(View.VISIBLE);

                                        TaiKhoan userCreated = new TaiKhoan(mail,pass,name,phone);

                                        if (mAuth.getCurrentUser().getUid() == null){
                                            Log.d(TAG, "savedInstanceState is null");
                                        }
                                        FirebaseDatabase.getInstance().getReference("users")
                                                .child(mAuth.getCurrentUser().getUid())
                                                .setValue(userCreated).addOnCompleteListener(new OnCompleteListener<Void>() {

                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {      // Check if the user information was stored in the DB

                                                if (task.isSuccessful()) {
                                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                    user.sendEmailVerification();
                                                    AlertDialog.Builder b = new AlertDialog.Builder(Registration.this);
                                                    b.setTitle("Th??ng b??o");
                                                    b.setMessage("????ng k?? t??i kho???n m???i th??nh c??ng! Ki???m tra email ????? x??c nh???n t??i kho???n! ");
                                                    b.setPositiveButton("K???t th??c!", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            Intent intent = new Intent(Registration.this, MainActivity.class);
                                                            startActivity(intent);

                                                        }
                                                    });
                                                    AlertDialog al = b.create();
                                                    al.show();


                                                }

                                                else {

                                                    Toast.makeText(Registration.this, "X???y ra l???i trong qu?? tr??nh ????ng k??! Xin th??? l???i sau", Toast.LENGTH_LONG).show();

                                                }

                                            }

                                        });

                                    }

                                    else {

                                        // Alert dialog builder

                                        AlertDialog.Builder builder_ProblemSigningUp = new AlertDialog.Builder(Registration.this);

                                        builder_ProblemSigningUp.setTitle("Th??ng b??o");

                                        builder_ProblemSigningUp.setMessage("T??i kho???n email ???? ???????c ????ng k??! Vui l??ng ki???m tra l???i");

                                        builder_ProblemSigningUp.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int which) {

                                                // Executed code when "OK" button is pressed

                                            }
                                        });

                                        builder_ProblemSigningUp.setIcon(android.R.drawable.ic_dialog_alert);


                                        // Alert dialog using the builder

                                        AlertDialog dialog_ProblemSigningUp = builder_ProblemSigningUp.create();

                                        dialog_ProblemSigningUp.show();

                                        dialog_ProblemSigningUp.setCancelable(false);

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
    public boolean validateEmail() {

        String emailInput = email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            email.setError("Tr?????ng email kh??ng ???????c ????? tr???ng");
            return false;
        }

        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Vui l??ng ??i???n ????ng email");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    public boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.setError("Tr?????ng m???t kh???u kh??ng ???????c ????? tr???ng");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("M???t kh???u kh??ng ????? m???nh. M???t kh???u ph???i t???i thi???u 8 k?? t???, kh??ng k?? t??? tr???ng, v?? ch???a c??c k?? t??? ?????c bi???t");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
    public boolean validateName() {
        String nameInput = hoten.getText().toString();
        if (nameInput.isEmpty()){
            hoten.setError("Thi???u th??ng tin h??? t??n");
            return false;
        }
        return true;
    }
    public boolean validatePhone() {
        String nameInput = sodienthoai.getText().toString();
        if (nameInput.isEmpty()){
            sodienthoai.setError("Thi???u th??ng tin s??? ??i???n tho???i");
            return false;
        }
        return true;
    }
//    public boolean validateUsername() {
//        String nameInput = username.getText().toString();
//        if (nameInput.isEmpty()){
//            username.setError("Thi???u th??ng tin t??n ????ng nh???p");
//            return false;
//        }
//        return true;
//    }
    public boolean validateConfirm() {
        String password1 = password.getText().toString();
        String password2 = passwordCF.getText().toString();
        if (password2.isEmpty()) {
            passwordCF.setError("Tr?????ng m???t kh???u kh??ng ???????c ????? tr???ng");
            return false;
        }else
        if (password2.equals(password1)==false){
            passwordCF.setError("Kh??ng tr??ng v???i m???t kh???u ???? nh???p");
            return false;
        }
        else
        {
            passwordCF.setError(null);
            return true;
        }
    }
//    public void confirmInput(View v) {
//        if (!validateEmail() | !validatePassword() | !validateName() | !validatePhone()| !validateUsername()|validateConfirm()) {
//            return;
//        }
//    }
}