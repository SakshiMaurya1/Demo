package com.example.demonew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddDb extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "InfoDatabase.db";
    public static final String TABLE_NAME = "userdata";
    public static final String C1 = "id";
    public static final String C2 = "username";
    public static final String C3 = "phone";
    public static final String C4 = "email";
    public static final String C5 = "password";
    public static final String C6 = "confirm_password";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userdata" + "(id integer primary key autoincrement,username text,phone text,email text,password text,confirm_password text)");
    }

    public AddDb(Context cn) {

        super(cn, DATABASE_NAME, null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS userdata");
        onCreate(db);
    }

    public boolean insertContact(String n, String num, String em, String pswd, String cpswd) {
        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put("username", n);
        cn.put("phone", num);
        cn.put("email", em);
        cn.put("password", pswd);
        cn.put("confirm_password", cpswd);
        sq.insert("userdata", null, cn);
        Log.e("TAG", "insertContact: "+cn );
        return true;
    }


    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getAll() {
        ArrayList<HashMap<String, String>> ar2 = new ArrayList<>();
        SQLiteDatabase sq = this.getReadableDatabase();

        List<RegModal> regModalList =new ArrayList<RegModal>();

        Cursor csr = sq.rawQuery("select * from userdata", null);
        csr.moveToFirst();
        while (!csr.isAfterLast()) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("id", csr.getString(csr.getColumnIndex(C1)));
            hm.put("name", csr.getString(csr.getColumnIndex(C2)));
            hm.put("phone", csr.getString(csr.getColumnIndex(C3)));
            hm.put("email", csr.getString(csr.getColumnIndex(C4)));
            hm.put("password", csr.getString(csr.getColumnIndex(C5)));
            hm.put("confirm_password", csr.getString(csr.getColumnIndex(C6)));
            ar2.add(hm);

            regModalList.add(new RegModal(csr.getString(csr.getColumnIndex(C2)),
                            csr.getString(csr.getColumnIndex(C3)),
                            csr.getString(csr.getColumnIndex(C4)),
                            csr.getString(csr.getColumnIndex(C5)),
                            csr.getString(csr.getColumnIndex(C6))));

            //Log.e("check2", "checking");
            csr.moveToNext();
        }
        if (csr != null) {
            csr.close();
        }

        if(regModalList.size()>0)
        {
            DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("userdata");
            for(RegModal r : regModalList)
            {
                ref.push().setValue(r);
            }
        }
        return ar2;
    }

    public void updateContact(String id, String namedt, String numedt, String emedt, String passedt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(C2, namedt);
        val.put(C3, numedt);
        val.put(C4, emedt);
        val.put(C5, passedt); 
        int update = db.update("userdata", val, "id=?", new String[]{id});

        db.close();

    }

    public void deleteContact(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{id});
        db.close();
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from userdata where email = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            Log.e("Email", "Validate==" + cursor.getCount());
            return true;
        }
        else
            return false;
    }
}
