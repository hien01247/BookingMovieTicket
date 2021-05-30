package com.example.bookingmovie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.bookingmovie.Database.DatabaseUser;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment {
    private Button buttonSign;
    private  Button  buttonReg;
    private TextInputEditText textViewUser;
    private  TextInputEditText textViewPass;
    private TextView txForget;
    private CheckBox checkBoxRem;
    DatabaseUser DB;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login,container,false);
        buttonReg = v.findViewById(R.id.btn_Reg);
        buttonSign = v.findViewById(R.id.btn_Sign);
        textViewUser = v.findViewById(R.id.txt_Email_SDT);
        textViewPass = v.findViewById(R.id.txt_pass);
        DB = new DatabaseUser(getActivity());
        txForget = v.findViewById(R.id.txt_Forget);
        checkBoxRem = v.findViewById(R.id.cbo_RemMe);

        SharedPreferences preferences = getActivity().getSharedPreferences("checkbox", Context.MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if (checkbox.equals("true")){
            Intent intent = new Intent(getContext(),MainActivity.class);
            startActivity(intent);
        }
        checkBoxRem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    SharedPreferences preferences = getActivity().getSharedPreferences("checkbox", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(getContext(),"Checked",Toast.LENGTH_SHORT).show();
                } else if (!buttonView.isChecked()){{
                    SharedPreferences preferences = getActivity().getSharedPreferences("checkbox", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(getContext(),"UnChecked",Toast.LENGTH_SHORT).show();
                }}
            }
        });
        txForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),forgetpass1.class);
                startActivity(intent);
            }
        });
        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = textViewUser.getText().toString();
                String pass = textViewPass.getText().toString();
                String nameuser=null;
                if (user.equals("")||pass.equals(""))
                    Toast.makeText(getActivity(),"Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkPassword(user,pass);
                    if (checkuserpass == true){
                        Cursor cursor = DB.getData();
                        while (cursor.moveToNext()){
                            if (cursor.getString(0).equals(user)){
                                nameuser = cursor.getString(3);
                                break;
                            }
                        }

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(getString(R.string.isLogin),"true");
                        editor.commit();

                        Toast.makeText(getActivity(),"Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("username",user);
                        intent.putExtra("name",nameuser);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getActivity(),"Vui lòng kiểm tra lại thông tin đăng nhập", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registration = new Intent(getActivity(), Registration.class);
                startActivity(registration);
            }
        });
        return v;
    }
}
