package com.example.hms2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Assign extends AppCompatActivity {

    private EditText patid;
    private EditText docid;
    private EditText assign_date;
    private Button btn_assign;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);

        patid = (EditText) findViewById(R.id.pid);
        docid = (EditText) findViewById(R.id.did);
        assign_date = (EditText) findViewById(R.id.assigndate);
        btn_assign = (Button) findViewById(R.id.btn_assign);
        DB = new DBHelper(this);

        btn_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pat = patid.getText().toString();
                String doc = docid.getText().toString();
                String date = assign_date.getText().toString();

                if(pat.equals("")||doc.equals("")||assign_date.equals(""))
                    Toast.makeText(Assign.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkpat = DB.checkPatient(Integer.parseInt(pat));
                    Boolean checkdoc = DB.checkDoctor(Integer.parseInt(doc));

                    if(checkpat==true){

                        if(checkdoc==true){

                            Boolean insert = DB.insertDataAssign(Integer.parseInt(pat), Integer.parseInt(doc), date);

                            if(insert==true){
                                Toast.makeText(Assign.this, "Doctor Assigned Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(Assign.this, "Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(Assign.this, "The doctor ID does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Assign.this, "The patient ID does not exist!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}