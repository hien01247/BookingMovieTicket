package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.cinemaapp.model.TaiKhoan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class AccountInfo extends AppCompatActivity {
    private TextInputEditText hoten;
    private TextInputEditText email;
    private TextInputEditText sdt;
    private ImageView imageView_cpUserImage;

    Button update;

    TaiKhoan taiKhoan;
    Uri imageSelected;

    private String currentUserName;
    private String currentUserPhone;

    private boolean successfulUpdate = true;


    private FirebaseAuth mAuth;

    private DatabaseReference userRef;

    private StorageReference storageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông tin tài khoản");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        mAuth = FirebaseAuth.getInstance();

        userRef = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid());

        storageRef = FirebaseStorage.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid());

        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("username","Tên đăng nhập");
        String gAuth = sharedPreferences.getString("gAuth","false");


        //taiKhoan = db.getTaiKhoanByUsername(user);

        hoten = findViewById(R.id.txt_Acc_name);
        email = findViewById(R.id.txt_Acc_email);
        sdt = findViewById(R.id.txt_Acc_sdt);
        email.setEnabled(false);
        imageView_cpUserImage = findViewById(R.id.imageView_cpUserImage2);

        userRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // Save current user data in local variables
                currentUserName = snapshot.child("hoTen").getValue(String.class);
                currentUserPhone = snapshot.child("sdt").getValue(String.class);

                // Set current user data on the activity
                hoten.setText(currentUserName);
                sdt.setText(currentUserPhone);

                email.setText(mAuth.getCurrentUser().getEmail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(AccountInfo.this, "Có lỗi xảy ra khi tải dữ liệu. Xin thử lại.", Toast.LENGTH_LONG).show();

            }

        });

        if (mAuth.getCurrentUser().getPhotoUrl() != null) {

            Picasso.with(AccountInfo.this)
                    .load(mAuth.getCurrentUser().getPhotoUrl())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.img_loading)
                    .into(imageView_cpUserImage);

        }
        if (imageSelected != null) {

            StorageReference fileRef = storageRef.child("profile-Image.jpg");


            fileRef.putFile(imageSelected)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // The image was uploaded to Firebase successfully!


                            // Link the image URL with the user profile image
                            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                                @Override
                                public void onSuccess(Uri uri) {

                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setPhotoUri(uri)
                                            .build();

                                    mAuth.getCurrentUser().updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {

                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {

                                                Toast.makeText(AccountInfo.this," Cập nhật thành công", Toast.LENGTH_LONG).show();

                                            }

                                            else {

                                                Toast.makeText(AccountInfo.this, "Lỗi", Toast.LENGTH_LONG).show();

                                            }

                                        }

                                    });

                                }

                            });

                        }

                    })

                    .addOnFailureListener(new OnFailureListener() {

                        @Override
                        public void onFailure(@NonNull Exception e) {



                            Toast.makeText(AccountInfo.this,"Lỗi", Toast.LENGTH_LONG).show();

                        }

                    });

        }
        imageView_cpUserImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final CharSequence[] options = { "Chụp ảnh", "Chọn trong thư viện ảnh","Thoát" };
                AlertDialog.Builder builder = new AlertDialog.Builder(AccountInfo.this);
                builder.setTitle("Chọn hình ảnh");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Chụp ảnh"))
                        {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1001);
                        }
                        else if (options[item].equals("Chọn trong thư viện ảnh"))
                        {
                            Intent intentOpenGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                            startActivityForResult(intentOpenGallery, 1000);

                        }
                        else if (options[item].equals("Thoát")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();

            }

        });

        update =findViewById(R.id.btn_capnhap);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = hoten.getText().toString();
                String phone = sdt.getText().toString();
                String mail = email.getText().toString();
                if (name.isEmpty()) {

                    hoten.setError("Tên không được để trống");
                    hoten.requestFocus();
                    return;

                }

                else if (phone.isEmpty()) {

                    sdt.setError("Điện thoại không được để trống");
                    sdt.requestFocus();
                    return;

                }

                else if (mail.isEmpty()) {

                    email.setError("Email không được để trống");
                    email.requestFocus();
                    return;

                }

                else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {

                    email.setError("Email không đúng! Xin kiểm tra lại");
                    email.requestFocus();
                    return;

                }
                else if (phone.length()<10 && phone.length()>11){

                    sdt.setError("Số điện thoại không đúng");
                    sdt.requestFocus();
                    return;

                }
                else{

                    userRef.child("hoTen").setValue(name);
                    userRef.child("sdt").setValue(phone);
                    mAuth.getCurrentUser().updateEmail(mail);

                    AlertDialog.Builder b = new AlertDialog.Builder(AccountInfo.this);
                    b.setTitle("Thông báo");
                    b.setMessage("Cập nhập thành công!!!");
                    b.setPositiveButton("Kết thúc!", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(AccountInfo.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {

            // If a image from the gallery was selected
            if (resultCode == Activity.RESULT_OK) {

                // Get the image an load it
                imageSelected = data.getData();

                Picasso.with(AccountInfo.this)
                        .load(imageSelected)
                        .fit()
                        .centerCrop()
                        .into(imageView_cpUserImage);

            }

        }
        else if (requestCode == 1001){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,bytes);
            String path=MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(),imageBitmap,"val",null);
            imageSelected=Uri.parse(path);

            Picasso.with(AccountInfo.this)
                    .load(imageSelected)
                    .fit()
                    .centerCrop()
                    .into(imageView_cpUserImage);
        }
        if (imageSelected != null) {

            StorageReference fileRef = storageRef.child("profile-Image.jpg");


            fileRef.putFile(imageSelected)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // The image was uploaded to Firebase successfully!


                            // Link the image URL with the user profile image
                            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                                @Override
                                public void onSuccess(Uri uri) {

                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setPhotoUri(uri)
                                            .build();

                                    mAuth.getCurrentUser().updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {

                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {

                                                Toast.makeText(AccountInfo.this,"Load ảnh thành công", Toast.LENGTH_LONG).show();

                                            }

                                            else {

                                                Toast.makeText(AccountInfo.this, "Lỗi", Toast.LENGTH_LONG).show();

                                            }

                                        }

                                    });

                                }

                            });

                        }

                    })

                    .addOnFailureListener(new OnFailureListener() {

                        @Override
                        public void onFailure(@NonNull Exception e) {



                            Toast.makeText(AccountInfo.this,"Lỗi", Toast.LENGTH_LONG).show();

                        }

                    });

        }

    }
}