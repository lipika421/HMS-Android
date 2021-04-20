package com.example.hms2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "HMS.db";

    public DBHelper(Context context) {
        super(context, "HMS.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table admin(admin_id INTEGER primary key, password TEXT)");
        MyDB.execSQL("create Table patient(patient_id INTEGER primary key, patient_name TEXT, age INTEGER)");
        MyDB.execSQL("create Table doctor(doctor_id INTEGER primary key, doctor_name TEXT, degree TEXT)");
        MyDB.execSQL("create Table assign(assign_id INTEGER primary key, patient_id INTEGER, doctor_id INTEGER, date_of_assign TEXT, date_of_release TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists admin");
        MyDB.execSQL("drop Table if exists patient");
        MyDB.execSQL("drop Table if exists doctor");
        MyDB.execSQL("drop Table if exists assign");
        onCreate(MyDB);
    }

    public Boolean insertDataAdmin(int id, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("admin_id", id);
        contentValues.put("password", password);
        long result = MyDB.insert("admin", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean insertDataPatient(String name, int age) {

        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("Select * from patient", null);
        int id = cursor.getCount() + 1;

        ContentValues contentValues = new ContentValues();
        contentValues.put("patient_id", id);
        contentValues.put("patient_name", name);
        contentValues.put("age", age);

        long result = MyDB.insert("patient", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean insertDataDoctor(String name, String degree) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("Select * from doctor", null);
        int id = cursor.getCount() + 1;

        ContentValues contentValues = new ContentValues();
        contentValues.put("doctor_id", id);
        contentValues.put("doctor_name", name);
        contentValues.put("degree", degree);
        long result = MyDB.insert("doctor", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean insertDataAssign(int pat_id, int doc_id, String assign_date) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("Select * from assign", null);
        int id = cursor.getCount() + 1;

        ContentValues contentValues = new ContentValues();
        contentValues.put("assign_id", id);
        contentValues.put("patient_id", pat_id);
        contentValues.put("doctor_id", doc_id);
        contentValues.put("date_of_assign", assign_date);
        contentValues.put("date_of_release", "");

        long result = MyDB.insert("assign", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean updateDataAssign(int assign_id, String release_date) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from assign where assign_id = ? and date_of_release = ?", new String[]{Integer.toString(assign_id), ""});
        if (res.getCount() > 0) {

            db.execSQL("UPDATE assign SET date_of_release = ? WHERE assign_id = ?", new String[] {release_date, Integer.toString(assign_id)});
            return true;
        }

        return false;
    }

    public Boolean checkadminpassword(int id, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where admin_id = ? and password = ?", new String[] {Integer.toString(id),password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkDoctor(int id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from doctor where doctor_id = ?", new String[] {Integer.toString(id)});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkPatient(int id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from patient where patient_id = ?", new String[] {Integer.toString(id)});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkAssign(int id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from assign where assign_id = ?", new String[] {Integer.toString(id)});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

}
