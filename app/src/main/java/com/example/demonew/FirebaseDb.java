package com.example.demonew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;

public class FirebaseDb extends AppCompatActivity {
    EditText edit_name,edit_email;
    Button enterdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_db);
        edit_name=findViewById(R.id.edit_name);
        edit_email=findViewById(R.id.edit_email);
        enterdata=findViewById(R.id.enterdata);
        DAOInfo dao=new DAOInfo();
        enterdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Info info=new Info(edit_name.getText().toString(),edit_email.getText().toString());
                dao.add(info);
            }
        });
    }
}