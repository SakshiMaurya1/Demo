package com.example.demonew;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAdapter extends BaseAdapter {

    private final ArrayList<HashMap<String, String>> ar3;
    private final AddDb adb;
    final Seconddemo context;

    public MyAdapter(Seconddemo second, ArrayList<HashMap<String, String>> hm)
    {
        adb=new AddDb(second);
        context=second;
        ar3=hm;
    }

    @Override
    public int getCount() {
        return ar3.size();
    }

    @Override
    public Object getItem(int position) {
        return ar3.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View res= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);

        HashMap<String, String> map = ar3.get(position);
        TextView tv1,tv2,tv3,tv4;
        Button upd,del;
        LinearLayout lr;
        lr=res.findViewById(R.id.Layout1);
        tv1=res.findViewById(R.id.View1);
        tv2=res.findViewById(R.id.View2);
        tv3=res.findViewById(R.id.View3);
        upd=res.findViewById(R.id.upd);
        del=res.findViewById(R.id.del);
        tv1.setText(map.get("name"));
        tv2.setText(map.get("phone"));
        tv3.setText(map.get("email"));

        String id=map.get("id");
        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MainActivity.class).putExtra("id",id);
                context.startActivity(intent);

            }
        });
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                builder.setMessage("Delete this item?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adb.deleteContact(id);
                        context.Setdata();
                        Toast.makeText(context,"Data is deleted!",Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.Setdata();
                    }
                });

                AlertDialog dialog=builder.create();
                dialog.setTitle("Alert");
                dialog.show();

            }
        });

        lr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ContactProfile.class).putExtra("hashmap",map);
                context.startActivity(intent);
            }
        });

        return res;

    }
    public void Clear(){
        ar3.clear();
    }

}