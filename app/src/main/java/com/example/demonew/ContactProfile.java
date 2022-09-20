package com.example.demonew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.telephony.SmsManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactProfile extends AppCompatActivity {
    TextView contactname, contactnum, contactemail, sms, whatsapp, pref;
    HashMap<String, String> map;
    ImageView img1;
    Button logout;
    private final static int permissioncode = 100;
    private final static int smspermission = 10;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_profile);
        img1=findViewById(R.id.img1);
        contactname = findViewById(R.id.contactname);
        contactnum = findViewById(R.id.contactnum);
        contactemail = findViewById(R.id.contactemail);
        sms = findViewById(R.id.sms);
        whatsapp = findViewById(R.id.whatsapp);
        pref = findViewById(R.id.prefcheck);
        logout = findViewById(R.id.logout);
        sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        Boolean prefer = sharedPreferences.getBoolean("Validity", false);
        Log.e("Checking", "preference==" + prefer);
        Intent i = getIntent();
        map = (HashMap<String, String>) i.getSerializableExtra("hashmap");
//        contactname.setText(map.get("name"));
//        contactnum.setText(Html.fromHtml("<font color='Aqua'><u>" + map.get("phone") + "</u></font>"));
//        contactemail.setText(Html.fromHtml("<font color='Aqua'><u>" + map.get("email") + "</u></font>"));
//        pref.setText(String.valueOf(prefer));

        if(map==null)
        {
            GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);
            contactname.setText(account.getDisplayName());
            contactemail.setText(account.getEmail());
            Glide.with(this).load(account.getPhotoUrl()).into(img1);

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(ContactProfile.this,FireBaseGAuth.class));
                    finish();
                }
            });
        }

        contactnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(ContactProfile.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(ContactProfile.this, new String[]{Manifest.permission.CALL_PHONE}, permissioncode);

                } else if (ActivityCompat.checkSelfPermission(ContactProfile.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactnum.getText().toString()));
                    startActivity(intent);
                }
            }
        });

        contactemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectorIntent = new Intent(Intent.ACTION_SENDTO);
                selectorIntent.setData(Uri.parse("mailto:"));
                final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{contactemail.getText().toString()});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, " Mail from Sakshi!");
                emailIntent.setSelector(selectorIntent);
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SMSCall();
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected()) {
                    sendMessage();
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(ContactProfile.this, new String[]{Manifest.permission.INTERNET}, 200);
                }
            }
        });
    }

    private void SMSCall() {
        if (ActivityCompat.checkSelfPermission(ContactProfile.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + contactnum.getText().toString()));
            intent.putExtra("sms_body", "Hey there! " + contactname.getText().toString());
            startActivity(intent);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ContactProfile.this, Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(ContactProfile.this, new String[]{Manifest.permission.SEND_SMS}, smspermission);
                Log.e("SAKSHI", "Requestcode=111=");
            } else {
                ActivityCompat.requestPermissions(ContactProfile.this, new String[]{Manifest.permission.SEND_SMS}, smspermission);
                Log.e("SAKSHI", "Requestcode=2222=");
            }
        }
    }


    private void sendMessage() {
        PackageManager packageManager = ContactProfile.this.getPackageManager();
        Intent i = new Intent(Intent.ACTION_VIEW);

        try {
            String url = "https://api.whatsapp.com/send?phone=+91"+ contactnum.getText().toString() +"&text=" + URLEncoder.encode("Hii! Message from Demo", "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                ContactProfile.this.startActivity(i);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        startActivity(i);
    }

    private boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            if(nInfo!=null)
            {
                if(nInfo.getType()== ConnectivityManager.TYPE_WIFI)
                {
                    Toast.makeText(ContactProfile.this,"Wifi Enabled",Toast.LENGTH_SHORT).show();
                    connected=true;
                }
                if(nInfo.getType()== ConnectivityManager.TYPE_MOBILE)
                {
                    Toast.makeText(ContactProfile.this,"Data Network Enabled",Toast.LENGTH_SHORT).show();
                    connected=true;
                }
            }
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        onRequestPermissionsResult(this, permissions, grantResults);
        if (requestCode == permissioncode) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];
                if (permission.equals(Manifest.permission.CALL_PHONE) && grantResult == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactnum.getText().toString()));
                    startActivity(intent);
                }
            }
        }
        else if (requestCode == smspermission) {
            Log.e("SAKSHI", "Requestcode==" + requestCode);
            Log.e("SAKSHI", "permissions==" + Arrays.toString(permissions));
            Log.e("SAKSHI", "grantResult==" + grantResults[0]);

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + contactnum.getText().toString()));
                intent.putExtra("sms_body", "Hey there! " + contactname.getText().toString());
                startActivity(intent);
            }else {
                Toast.makeText(ContactProfile.this , "Permission Denied..",Toast.LENGTH_LONG).show();
            }
        }
    }

 public static void onRequestPermissionsResult(final Context context, String[] permissions, int[] grantResults) {
        HashMap<CharSequence, Boolean> checkShowRationale = new HashMap<>();
     for (int i = 0; i < grantResults.length; i++) {
         if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
             try {
                 PackageManager packageManager = context.getPackageManager();
                 PermissionInfo permissionInfo = packageManager.getPermissionInfo(permissions[i], 0);
                 PermissionGroupInfo permissionGroupInfo = null;
                 if (permissionInfo.group != null) {
                     permissionGroupInfo = packageManager.getPermissionGroupInfo(permissionInfo.group, 0);
                 }
                    /*if (permissionGroupInfo != null) {
                        checkShowRationale.put(permissionGroupInfo.loadLabel(packageManager), ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissions[i]));
                    }*/

                 if (permissionGroupInfo != null) {
                     if (!permissionInfo.group.contains("UNDEFINED")) {
                         checkShowRationale.put(permissionGroupInfo.loadLabel(packageManager), ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissions[i]));
                     } else {

                         String Label = "";
                         Log.e("123","permission====>>>"+permissions[i]);
                         switch (permissions[i]) {
                             case "android.permission.ACCESS_FINE_LOCATION":
                             case "android.permission.ACCESS_COARSE_LOCATION":
                                 Label = "LOCATION";
                                 break;
                             case "android.permission.CAMERA":
                                 Label = "CAMERA";
                                 break;

                             case "android.permission.READ_CONTACTS":
                             case "android.permission.WRITE_CONTACTS":
                                 Label = "CONTACTS";
                                 break;

                             case "android.permission.CALL_PHONE":
                             case "android.permission.READ_PHONE_STATE":
                                 Label = "PHONE/TELE-PHONE";
                                 break;

                             case "android.permission.READ_CALL_LOG":
                                 Label = "CALL-LOGS";
                                 break;
                             case "android.permission.SEND_SMS":
                             case "android.permission.READ_SMS":
                                 Label = "SMS";
                                 break;
                             default:
                                 Label = "UNDEFINED";
                                 break;

                         }
                         checkShowRationale.put(Label, ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissions[i]));
                     }
                 }


             } catch (PackageManager.NameNotFoundException e) {
                 e.printStackTrace();
             }
             //permissions[i].split("\\.");
         }
     }
        final List<String> permissionsName = new ArrayList<>();
        if (checkShowRationale.containsValue(false)) {
            for (Map.Entry<CharSequence, Boolean> e : checkShowRationale.entrySet()) {
                Boolean value = e.getValue();
                if (value.equals(false)) {
                    CharSequence key = e.getKey();
                    permissionsName.add(String.valueOf(key));
                }
            }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(false);
                builder.setTitle("Permission is denied!");
                builder.setMessage("Please Allow " + permissionsName.toString().replace(",", ",").replaceAll("[\\[.\\]]", "") + " permissions to use this feature");
                builder.setPositiveButton("Ok", (dialog, which) -> {
                    dialog.dismiss();
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", context.getPackageName(), null));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                });
                builder.setNegativeButton("cancel", (dialog, i) -> dialog.dismiss());

                builder.show();
            }
    }
}