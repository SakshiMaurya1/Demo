package com.example.demonew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class DynamicEx extends AppCompatActivity {

    RelativeLayout rel;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_ex);
        rel = findViewById(R.id.RelativeV);
        TextView tvname = new TextView(this);
        tvname.setText("Enter Name");
        EditText dynamic_name = new EditText(this);

        TextView tvnum = new TextView(this);
        tvnum.setText("Enter Num");
        EditText dynamic_number = new EditText(this);

        TextView tvemail = new TextView(this);
        tvemail.setText("Enter  Email");
        EditText dynamic_email = new EditText(this);

        TextView tvpass = new TextView(this);
        tvpass.setText("Enter Password");
        EditText dynamic_pass = new EditText(this);


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvname.setLayoutParams(params);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        tvname.setId(1);
        rel.addView(tvname);

        RelativeLayout.LayoutParams param1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dynamic_name.setLayoutParams(param1);
        param1.addRule(RelativeLayout.BELOW, 1);
        dynamic_name.setId(2);
        rel.addView(dynamic_name);

        RelativeLayout.LayoutParams param2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvnum.setLayoutParams(param2);
        param2.addRule(RelativeLayout.BELOW, 2);
        tvnum.setId(3);
        rel.addView(tvnum);

        RelativeLayout.LayoutParams param3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dynamic_number.setLayoutParams(param3);
        param3.addRule(RelativeLayout.BELOW, 3);
        dynamic_number.setId(4);
        rel.addView(dynamic_number);

        RelativeLayout.LayoutParams param4 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvemail.setLayoutParams(param4);
        param4.addRule(RelativeLayout.BELOW, 4);
        tvemail.setId(5);
        rel.addView(tvemail);

        RelativeLayout.LayoutParams param5 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dynamic_email.setLayoutParams(param5);
        param5.addRule(RelativeLayout.BELOW, 5);
        dynamic_email.setId(6);
        rel.addView(dynamic_email);

        RelativeLayout.LayoutParams param6 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvpass.setLayoutParams(param6);
        param6.addRule(RelativeLayout.BELOW, 6);
        tvpass.setId(7);
        rel.addView(tvpass);

        RelativeLayout.LayoutParams param7 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dynamic_pass.setLayoutParams(param7);
        param7.addRule(RelativeLayout.BELOW, 7);
        dynamic_pass.setId(8);
        rel.addView(dynamic_pass);

        RelativeLayout.LayoutParams paramRad = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RadioGroup rg=new RadioGroup(this);
        RadioButton rb1=new RadioButton(this);
        RadioButton rb2=new RadioButton(this);
        rb1.setText("Male");
        rb2.setText("Female");
        rg.addView(rb1);
        rg.addView(rb2);
        rg.setId(9);
        paramRad.addRule(RelativeLayout.BELOW, 8);
        rg.setLayoutParams(paramRad);
        rel.addView(rg);

//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                RadioButton radioButton = findViewById(i);
//                Toast.makeText(getApplicationContext(),radioButton.getText(),Toast.LENGTH_SHORT).show();
//            }
//        });
//
        RelativeLayout.LayoutParams paramCheck1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        CheckBox check1=new CheckBox(this);
        check1.setText("Music");
        check1.setId(10);
        check1.setLayoutParams(paramCheck1);
        paramCheck1.addRule(RelativeLayout.BELOW,9);
        rel.addView(check1);

        RelativeLayout.LayoutParams paramCheck2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        CheckBox check2=new CheckBox(this);
        check2.setText("Dance");
        check2.setId(11);
        check2.setLayoutParams(paramCheck2);
        paramCheck2.addRule(RelativeLayout.BELOW,10);
        rel.addView(check2);

        RelativeLayout.LayoutParams paramCheck3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        CheckBox check3=new CheckBox(this);
        check3.setText("Drama");
        check3.setId(12);
        check3.setLayoutParams(paramCheck3);
        paramCheck3.addRule(RelativeLayout.BELOW,11);
        rel.addView(check3);

        RelativeLayout.LayoutParams paramSeek = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        SeekBar seekBar=new SeekBar(this);
        seekBar.setId(13);
        seekBar.setLayoutParams(paramSeek);
        paramSeek.addRule(RelativeLayout.BELOW,12);
        rel.addView(seekBar);

        RelativeLayout.LayoutParams parambtn=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button dynamic_btn=new Button(this);
        dynamic_btn.setText("Submit");
        parambtn.addRule(RelativeLayout.BELOW,13);
        dynamic_btn.setLayoutParams(parambtn);
        dynamic_btn.setId(14);
        rel.addView(dynamic_btn);
    }
}