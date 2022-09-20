package com.example.demonew;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    RecyclerView RView;
    AddDb adb;
    Context context;
    ArrayList<HashMap<String,String>> ar3;

    Button upd,del;
    LinearLayout lr;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv1,tv2,tv3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lr=itemView.findViewById(R.id.RLinear);
            tv1=itemView.findViewById(R.id.namerecycle);
            tv2=itemView.findViewById(R.id.numrecycle);
            tv3=itemView.findViewById(R.id.emailrecycle);
        }
    }

    public CustomAdapter(ArrayList<HashMap<String, String>> hm)
    {
        ar3=hm;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View res= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);
        return new ViewHolder(res);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        HashMap<String, String> map = ar3.get(position);
        Log.e("Custom","Adapter==="+ar3.get(position));
        holder.tv1.setText(map.get("name"));
        holder.tv2.setText(map.get("phone"));
        holder.tv3.setText(map.get("email"));
    }

    @Override
    public int getItemCount() {
        return ar3.size();
    }
}