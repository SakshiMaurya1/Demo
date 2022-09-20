package com.example.demonew;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText em,n,pswd,cpswd,num;
    Button sb;
    public int flag=0;
    TextView textView;
    long number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n=findViewById(R.id.name1);
        num=findViewById(R.id.contact1);
        em = findViewById(R.id.email1);
        pswd=findViewById(R.id.password1);
        cpswd=findViewById(R.id.password2);
        sb = findViewById(R.id.b1);
        textView=findViewById(R.id.s1);

        String id1=ver();
        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check= DataValidate(n,num,em,pswd,cpswd);
                if(check)
                {
                    String E1=n.getText().toString();
                    String E2=num.getText().toString();
                    String E3 = em.getText().toString();
                    String E4=pswd.getText().toString();
                    String E5=cpswd.getText().toString();
                    number=Long.parseLong(E2);
                    AddDb ad=new AddDb(MainActivity.this);
                    if(flag==0)
                    {
                        ad.insertContact(E1, E2, E3, E4, E5);
//                        DAOInfo daoInfo=new DAOInfo();
//                        RegModal regModal=new RegModal(E1,E3,E2,E4,E5);
//                        daoInfo.add(regModal);
                    }
                    else if(flag==1)
                    {

                        ad.updateContact(id1,E1,E2,E3,E4);
                    }
                    show();
                }
            }
        });
    }
    public String ver ()
    {
        Intent intent=getIntent();
        String id1=intent.getStringExtra("id");

        //Log.e("Sakshi","xyz="+id1);
        if(id1!=null)
        {
            flag=1;
            textView.setText("Update");
            sb.setText("Update");
            return id1;
        }
        else {
            flag = 0;
        }
        return null;
    }
    public void show()
    {
        Intent intent=new Intent(MainActivity.this,Seconddemo.class);
        startActivity(intent);
    }
    public boolean DataValidate(EditText n, EditText num, EditText em, EditText pswd, EditText cpswd)
    {
        String E1=n.getText().toString();
        String E2 = em.getText().toString();
        String E3=pswd.getText().toString();
        String E4=cpswd.getText().toString();
        if(E1.isEmpty() )
        {
            n.setError("please enter name");
            return false;
        }
        else if (E2.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(E2).matches())
        {
            em.setError("Enter your valid email");
            return false;
        }

        if(!E4.equals(E3))
        {
            cpswd.setError("password do not match");
            return false;
        }

        if(num.length()!=10)
        {
            num.setError("enter valid number");
            return false;
        }
        return true;
    }

    public void startAlert(){
        int i=6;
        Intent intent = new Intent(this, MyBroadCastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 100, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (i * 1000 * 3600), pendingIntent);
        Toast.makeText(this, "Alarm set in " + i + " hours",Toast.LENGTH_SHORT).show();
    }

}