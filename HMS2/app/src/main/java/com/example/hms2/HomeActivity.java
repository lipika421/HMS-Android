package com.example.hms2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button patient;
    private Button doctor;
    private Button assign;
    private Button discharge;
    private Button logout;
//    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        patient = (Button) findViewById(R.id.btnaddpat);
        doctor = (Button) findViewById(R.id.btnadddoc);
        assign = (Button) findViewById(R.id.btnassign);
        discharge = (Button) findViewById(R.id.btndischarge);
        logout = (Button) findViewById(R.id.btnlogout);

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), Patient.class);
                startActivity(intent);
            }
        });

        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), Doctor.class);
                startActivity(intent);
            }
        });

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), Assign.class);
                startActivity(intent);
            }
        });

        discharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), Discharge.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}