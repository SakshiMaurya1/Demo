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

public class V2Adapter extends ArrayAdapter<Modal2> {
    List<Modal2> vl2;
    Context context;
    public V2Adapter(List<Modal2> modal2s, Context context) {
        super(context, R.layout.vol2, modal2s);
        this.vl2=modal2s;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        V2Adapter.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.vol2, null, true);
        holder = new V2Adapter.ViewHolder();
        //getting text views
        holder.id2 = convertView.findViewById(R.id.id2);
        holder.namev = convertView.findViewById(R.id.namev);
        holder.vdateA = convertView.findViewById(R.id.vdateA);
        holder.vdateM = convertView.findViewById(R.id.vdateM);
        holder.vv = convertView.findViewById(R.id.vv);
        holder.qcount = convertView.findViewById(R.id.qcount);

        convertView.setTag(holder);
        //Getting the tutorial for the specified position
        Modal2 modal2 = vl2.get(position);

        holder.id2.setText(modal2.getId());
        holder.namev.setText(modal2.getName());
        holder.vdateA.setText(modal2.getDateadded());
        holder.vdateM.setText(modal2.getDatemod());
        holder.vv.setText(modal2.getV());
        holder.qcount.setText(modal2.getQuotecount());

        return convertView;
    }
    static class ViewHolder {
        TextView id2;
        TextView namev;
        TextView vdateA;
        TextView vdateM;
        TextView vv;
        TextView qcount;
    }
}
