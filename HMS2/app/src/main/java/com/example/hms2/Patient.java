package com.example.hms2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Patient extends AppCompatActivity {

    private EditText patname;
    private EditText age;
    private Button btn_addp;
    DBHelper DB;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        patname = (EditText) findViewById(R.id.patid);
        age = (EditText) findViewById(R.id.age);
        btn_addp = (Button) findViewById(R.id.btnaddp);
        DB = new DBHelper(this);

        btn_addp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = patname.getText().toString();
                String patage = age.getText().toString();

                if(name.equals("")||patage.equals(""))
                    Toast.makeText(Patient.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean insert = DB.insertDataPatient(name, Integer.parseInt(patage));
                    if(insert==true){
                        Toast.makeText(Patient.this, "Patient Added Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Patient.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}