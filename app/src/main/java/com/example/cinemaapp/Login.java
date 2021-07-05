package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinemaapp.model.TaiKhoan;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private Button buttonSign;
    private  Button  buttonReg;
    private Button button_gSignIn;
    private TextInputEditText textViewUser;
    private  TextInputEditText textViewPass;
    private TextView txForget;
    private FirebaseAuth mAuth;

    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN=123;

    //BookingCinemaDatabase DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng nhập");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        mAuth = FirebaseAuth.getInstance();

        buttonReg = findViewById(R.id.btn_Reg);
        buttonSign = findViewById(R.id.btn_Sign);
        button_gSignIn = findViewById(R.id.button_SI_gSignIn);
        textViewUser = findViewById(R.id.txt_Email_SDT);
        textViewPass = findViewById(R.id.txt_pass);

        txForget = findViewById(R.id.txt_Forget);

        txForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),forgetpass1.class);
                startActivity(intent);
            }
        });

        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = textViewUser.getText().toString();
                String pass = textViewPass.getText().toString();
                if (user.equals("")||pass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                }
                else {
                    mAuth.signInWithEmailAndPassword(user, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {        // Check if the sign in is successful

                                    if (task.isSuccessful()) {

                                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();

                                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("Login","true");
                                        editor.putString("username",user);
                                        editor.commit();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);

                                    }

                                    else {

                                        Toast.makeText(Login.this, "Kiểm tra lại thông tin", Toast.LENGTH_LONG).show();

                                    }

                                }

                            });
                }
            }
        });
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registration = new Intent(getApplicationContext(), Registration.class);
                startActivity(registration);
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        button_gSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();


                startActivityForResult(signInIntent, RC_SIGN_IN);

            }

        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {

            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseDatabase.getInstance().getReference("users").addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (!snapshot.hasChild(mAuth.getCurrentUser().getUid())) {
                                        TaiKhoan userCreated = new TaiKhoan(mAuth.getCurrentUser().getEmail(),"","","");

                                        // Save Uid of Google user and the email in Firebase Database
                                        FirebaseDatabase.getInstance().getReference("users")
                                                .child(mAuth.getCurrentUser().getUid())
                                                .setValue(userCreated).addOnCompleteListener(new OnCompleteListener<Void>() {

                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {      // Check if the Google user information was stored in the DB

                                                FirebaseUser user = mAuth.getCurrentUser();
                                                Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();

                                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString("Login","true");
                                                editor.putString("gAuth","true");
                                                editor.putString("username",user.getEmail());
                                                editor.commit();
                                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                                startActivity(intent);

                                            }

                                        });

                                    }
                                    else {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();

                                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("Login","true");
                                        editor.putString("gAuth","true");
                                        editor.putString("username",user.getEmail());
                                        editor.commit();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) { }

                            });


                        } else {
                            // If sign in fails, display a message to the user.

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