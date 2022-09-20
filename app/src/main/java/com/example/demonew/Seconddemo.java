package com.example.demonew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Seconddemo extends AppCompatActivity {
    MyAdapter adp;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconddemo);
        lv=findViewById(R.id.lv1);

        Setdata();
    }
    public void Setdata(){
        AddDb ad=new AddDb(Seconddemo.this);
        ArrayList<HashMap<String, String>> ard = ad.getAll();
        if(adp!=null){
            adp.Clear();
        }
        adp=new MyAdapter(this, ard);
        lv.setAdapter(adp);
    }
}