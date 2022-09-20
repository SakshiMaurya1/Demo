package com.example.demonew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Login extends AppCompatActivity {
    EditText emailid , password;
    Button submit, singup, database,recyclerbutton, dynamicbtn;
    String email , pass;
    int flag=0;
    SharedPreferences preferences;
    final static String MyPref="Preferences";
    AddDb addDb=new AddDb(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailid=findViewById(R.id.email1);
        password=findViewById(R.id.password1);
        submit=findViewById(R.id.login);

        MainActivity mn=new MainActivity();

        preferences=getSharedPreferences(MyPref, Context.MODE_PRIVATE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=emailid.getText().toString();
                pass=password.getText().toString();

                SharedPreferences.Editor editor=preferences.edit();
                if(email.isEmpty())
                {
                    emailid.setError("enter email");
                    flag=0;
                }
                else if(pass.isEmpty())
                {
                    password.setError("Enter password");
                    flag=0;
                }
                else
                    flag=1;

                if(flag==1) {
                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("RegModal");
                    String userId = ref.getKey();

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int flag=0;
                                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    Log.e("Asmita==>", "snapshot: " + dataSnapshot);
                                    RegModal regModal = dataSnapshot.getValue(RegModal.class);
                                    Log.e("Sakshi==>", "snapshot: " + regModal.getEmail());
                                    String em = regModal.getEmail();
                                    String p=regModal.getPass();
                                    Log.e("TAG", "emcheck: "+ em.equals(email));
                                    if (em.equals(email)) {
                                        flag=1;
                                        if(p.equals(pass)) {
                                            Intent intent = new Intent(Login.this, Seconddemo.class);
                                            startActivity(intent);
                                            Toast.makeText(Login.this, "User verified", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                        else
                                        {
                                            Toast.makeText(Login.this,"Password does not match",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                if(flag==0)
                                {
                                    Toast.makeText(Login.this,"Email not found",Toast.LENGTH_SHORT).show();
                                }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
//                    Boolean checkuser = addDb.checkusernamepassword(email, pass);
//                    //Log.e("Validate", "check=" + checkuser);
//                    if (checkuser == false) {
//                        Toast.makeText(Login.this, "Username and Password do not match", Toast.LENGTH_SHORT).show();
//
//                    } else {
//
//                        editor.putString("Email",email);
//                        editor.putString("Password",pass);
//
//
//                        Intent i = new Intent(Login.this, Seconddemo.class);
//                        startActivity(i);
//
//                    }
//                    editor.putBoolean("Validity",checkuser);
                    editor.apply();
//                    Log.e("Email","checkuser==="+checkuser);
                }
            }
        });
    }
}