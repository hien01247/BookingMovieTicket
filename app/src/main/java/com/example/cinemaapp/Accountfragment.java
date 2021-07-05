package com.example.cinemaapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.cinemaapp.model.TaiKhoan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Accountfragment extends Fragment {
    //BookingCinemaDatabase DB;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button btnDangXuat;
    private Button btnDoiMatKhau;
    private Button btnThongTinTaiKhoan;
    private TextView changeImg;
    private TextView  txtuser;
    private TextView txtdiem;
    private ImageView userImg;
    //TaiKhoan taiKhoan;

    private FirebaseAuth mAuth;

    private DatabaseReference userRef;

    private StorageReference storageRef;

    private String currentUserName="";
    private String currentUserPoint="";



    private static final int pic_id = 123;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v;

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","Tên đăng nhập");

        v = inflater.inflate(R.layout.fragment_account,container,false);

        mAuth = FirebaseAuth.getInstance();

        userRef = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid());

        storageRef = FirebaseStorage.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid());


        txtuser = v.findViewById(R.id.user);
        txtdiem = v.findViewById(R.id.diemtichluy);
        userImg = v.findViewById(R.id.user_img);
        btnDangXuat = v.findViewById(R.id.btnDangXuat);
        btnThongTinTaiKhoan = v.findViewById(R.id.btnThongTinTaiKhoan);
        btnDoiMatKhau = v.findViewById(R.id.btnDoiMatKhau);
        String gAuth = sharedPreferences.getString("gAuth","false");
        if (gAuth == "true"){
            btnDoiMatKhau.setVisibility(View.GONE);
        }

        userRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // Save current user data in local variables
                currentUserName = snapshot.child("hoTen").getValue(String.class);
                currentUserPoint = snapshot.child("diemTichLuy").getValue(Integer.class).toString();
                // Set current user data on the activity
                txtuser.setText(currentUserName);
                txtdiem.setText(currentUserPoint);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

               // Toast.makeText(AccountInfo.this, "Có lỗi xảy ra khi tải dữ liệu. Xin thử lại.", Toast.LENGTH_LONG).show();

            }

        });
        if (mAuth.getCurrentUser().getPhotoUrl() != null) {
            Picasso.with(getContext())
                    .load(mAuth.getCurrentUser().getPhotoUrl())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.img_loading)
                    .into(userImg);

        }
        btnThongTinTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AccountInfo.class);
                startActivity(intent);
            }
        });

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Login","false");
                editor.putString("gAuth","false");
                editor.putString("username","Tên đăng nhập");
                editor.commit();
                    Intent intent = new Intent(getContext(),MainActivity.class);
                    startActivity(intent);
                }
            });

        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(),ChangePassword.class);
                    startActivity(intent);
                }
            });


         return v;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == pic_id) {
            Bitmap photo = (Bitmap) data.getExtras()
                    .get("data");
            userImg.setImageBitmap(photo);
        }
    }
}
