package com.example.demonew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashMap;

public class MainScreen extends AppCompatActivity {
    Button login, singup, database,recyclerbutton, dynamicbtn, implicit, frgment, customAlert, json, retroEx, notif, grid, firebasedb, googleauth;
    NotificationManagerCompat managerCompat;
    Notification notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        login=findViewById(R.id.b1);
        singup=findViewById(R.id.b2);
        database=findViewById(R.id.q1);
        recyclerbutton=findViewById(R.id.recyc);
        dynamicbtn=findViewById(R.id.dynamic);
        implicit=findViewById(R.id.implicit);
        frgment=findViewById(R.id.frgment);
        customAlert=findViewById(R.id.customAlert);
        json=findViewById(R.id.json);
        retroEx=findViewById(R.id.retroEx);
        notif=findViewById(R.id.notif);
        grid=findViewById(R.id.grid);
        firebasedb=findViewById(R.id.firebasedb);
        googleauth=findViewById(R.id.googleauth);

//        Log.e("notif","data==="+data);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel("myCh","My channel",NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder=new NotificationCompat.Builder(MainScreen.this,"myCh")
                .setSmallIcon(R.drawable.img)
                .setContentTitle("Notification created")
                .setContentText("Notification from Demo");

        notification=builder.build();

        managerCompat=NotificationManagerCompat.from(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainScreen.this,Login.class);
                startActivity(i);
            }
        });

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainScreen.this,MainActivity.class);
                startActivity(i);
            }
        });

        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainScreen.this,Seconddemo.class);
                startActivity(i);

            }
        });

        recyclerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDb ad=new AddDb(MainScreen.this);
                ArrayList<HashMap<String,String>> arrayList=ad.getAll();
                Intent i=new Intent(MainScreen.this,RecyclerEx.class).putExtra("Hmap",arrayList);
                startActivity(i);
            }
        });

        dynamicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainScreen.this,DynamicEx.class);
                startActivity(i);
            }
        });

        implicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.enjayworld.com/"));
                startActivity(intent);
            }
        });

        frgment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(MainScreen.this,FragmentEx1.class);
//                startActivity(intent);
            }
        });

        customAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainScreen.this);
                AlertDialog alertDialog=builder.create();
                alertDialog.setTitle("Custom Alert");

                final View custom=getLayoutInflater().inflate(R.layout.custom_alert,null);

                alertDialog.setView(custom);
                Button alertbtn=custom.findViewById(R.id.alertbtn);
                Button alertdismiss=custom.findViewById(R.id.alertdismiss);
                alertbtn .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText editText = custom.findViewById(R.id.alertname);
                        String data=editText.getText().toString();
                        if(data.isEmpty())
                        {
                            Toast.makeText(getApplicationContext(), "Please enter your name ", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            sendData(data);
                            alertDialog.dismiss();
                        }
                    }
                });

                alertdismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                                Toast.makeText(getApplicationContext(),"Dialog Dismissed",Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });

        json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG", "onClick: ");
               Intent intent=new Intent(MainScreen.this,VolleyEx.class);
               startActivity(intent);
            }
        });

        retroEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainScreen.this, RetrofitExample.class);
                startActivity(intent);
            }
        });

        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainScreen.this,GridActivity.class);
                startActivity(intent);
            }
        });

        firebasedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainScreen.this,FirebaseDb.class);
                startActivity(intent);
            }
        });

        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                    }
                });

        googleauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainScreen.this,FireBaseGAuth.class);
                startActivity(intent);
            }
        });

   }

    public void addNotification(View view) {
        managerCompat.notify(1,notification);
    }

    private void sendData(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }


}