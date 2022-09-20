package com.example.demonew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class VAdapter extends ArrayAdapter<VolleyModal> {
    List<VolleyModal> vlist;
    Context context;
    public VAdapter(List<VolleyModal> volleyModalList, Context context) {
        super(context, R.layout.volleylist, volleyModalList);
        this.vlist=volleyModalList;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.volleylist, null, true);
        holder = new ViewHolder();
        //getting text views
        holder.uid = convertView.findViewById(R.id.UID);
        holder.id = convertView.findViewById(R.id.id);
        holder.jtitle = convertView.findViewById(R.id.jtitle);
        holder.jbody = convertView.findViewById(R.id.jbody);

        convertView.setTag(holder);
        //Getting the tutorial for the specified position
        VolleyModal volleyModal = vlist.get(position);

        holder.uid.setText(volleyModal.getUserID());
        holder.id.setText(volleyModal.getId());
        holder.jtitle.setText(volleyModal.getTitle());
        holder.jbody.setText(volleyModal.getBody());

        return convertView;
    }
    static class ViewHolder {
        TextView uid;
        TextView id;
        TextView jtitle;
        TextView jbody;
    }
}
