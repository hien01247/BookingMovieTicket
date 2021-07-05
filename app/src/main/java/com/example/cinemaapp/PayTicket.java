package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;


public class PayTicket extends AppCompatActivity {
    private EditText etCardOwner, etCardNumber, etExpiration;
    private Button btnCancel, btnConfirm;
    private TextView txtDescription;
    private Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_ticket);
        etCardNumber = findViewById(R.id.etCardNumber);
        etCardOwner = findViewById(R.id.etCardOwner);
        etExpiration = findViewById(R.id.etCardExpiration);
        btnCancel = findViewById(R.id.btnCancel);
        btnConfirm = findViewById(R.id.btnConfirm);
        txtDescription = findViewById(R.id.txtDescription);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCardNumber.getText().toString().isEmpty() || etCardOwner.getText().toString().isEmpty() || etExpiration.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(),TicketDetailActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    /*private void setupDatePicker() {
        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(getActivity(), this::updateExpirationDate,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));

        etExpiration.setOnClickListener(v -> builder.setActivatedMonth(Calendar.JANUARY)
                .setMinYear(2018)
                .setActivatedYear(2019)
                .setMaxYear(2050)
                .setMonthRange(Calendar.JANUARY, Calendar.DECEMBER).build().show());
    }
    private void updateExpirationDate(int selectedMonth, int selectedYear) {
        etExpiration.setText((selectedMonth + 1) + "/" + selectedYear);
    }*/
}