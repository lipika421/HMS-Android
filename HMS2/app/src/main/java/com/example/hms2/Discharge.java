package com.example.hms2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Discharge extends AppCompatActivity {

    private EditText assignid;
    private EditText discharge_date;
    private Button btn_discharge;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discharge);

        assignid = (EditText) findViewById(R.id.assignid);
        discharge_date = (EditText) findViewById(R.id.dischargedate);
        btn_discharge = (Button) findViewById(R.id.btn_discharge);
        DB = new DBHelper(this);

        btn_discharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String assign = assignid.getText().toString();
                String date = discharge_date.getText().toString();

                if(assign.equals("")||date.equals(""))
                    Toast.makeText(Discharge.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkassign = DB.checkAssign(Integer.parseInt(assign));
                    if(checkassign==true){

//                        Toast.makeText(Discharge.this, "Reached 1!", Toast.LENGTH_SHORT).show();
                        Boolean insert = DB.updateDataAssign(Integer.parseInt(assign), date);
//                        Toast.makeText(Discharge.this, "Reached 2!", Toast.LENGTH_SHORT).show();

                        if(insert==true){
                            Toast.makeText(Discharge.this, "Patient Discharged Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Discharge.this, "The Patient is already discharged!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Discharge.this, "This Assign ID does not exist!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}