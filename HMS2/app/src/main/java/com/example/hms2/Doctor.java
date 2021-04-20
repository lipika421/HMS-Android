package com.example.hms2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Doctor extends AppCompatActivity {

    private EditText docname;
    private EditText degree;
    private Button btn_addd;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        docname = (EditText) findViewById(R.id.docid);
        degree = (EditText) findViewById(R.id.degree);
        btn_addd = (Button) findViewById(R.id.btnaddd);
        DB = new DBHelper(this);

        btn_addd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = docname.getText().toString();
                String deg = degree.getText().toString();

                if(name.equals("")||deg.equals(""))
                    Toast.makeText(Doctor.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean insert = DB.insertDataDoctor(name, deg);
                    if(insert==true){
                        Toast.makeText(Doctor.this, "Doctor Added Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Doctor.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}