package com.example.demonew;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context gridcontext;
    public int [] pics=new int[]{
            R.drawable.cimg,
            R.drawable.cppimg,
            R.drawable.java,
            R.drawable.python,
            R.drawable.kotlin,
            R.drawable.swift,
            R.drawable.ruby,
            R.drawable.css};
    public ImageAdapter(Context context) {
        gridcontext=context;
    }

    @Override
    public int getCount() {
        return pics.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;

        if (view == null) {
            imageView = new ImageView(gridcontext);
            imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) view;
        }
        imageView.setImageResource(pics[i]);
        return imageView;
    }
}
