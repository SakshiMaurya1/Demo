package com.example.demonew;

import static android.widget.Toast.LENGTH_LONG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerEx extends AppCompatActivity {
    int count=0;
    RecyclerView recyclerView;
    ArrayList<HashMap<String,String>> arrayList;
    CustomAdapter adapter;
    AddDb adb;
    NestedScrollView NestedSV;
    ProgressBar loadingPB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_ex);

        adb=new AddDb(RecyclerEx.this);

        Intent i=getIntent();
        arrayList= (ArrayList<HashMap<String, String>>) i.getSerializableExtra("Hmap");
        Log.e("List","Data=="+arrayList);
        recyclerView = (RecyclerView) findViewById(R.id.Recycler);
//        NestedSV=findViewById(R.id.nested);
//        loadingPB=findViewById(R.id.idPBLoading);
        adapter = new CustomAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
//        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(recyclerView);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
//        NestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if(scrollY==v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){
//                    count++;
//                    loadingPB.setVisibility(View.VISIBLE);
//                    HashMap<String,String> hm=new HashMap<String,String>();
//                    ArrayList<HashMap<String,String>> arrayList=adb.getAll();
//
////                            hm = adb.gethash(i);
//                            arrayList.add(hm);
//                            adapter=new CustomAdapter(arrayList);
//                            recyclerView.setAdapter(adapter);
//                    }
//                }

    }
//    HashMap<String, String> deletedmap=null;
//    String deleted=null;

//
//    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//        @Override
//        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//            return false;
//        }
//
//        @Override
//        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//            int position = 0;
//            String pos=Integer.toString(position);
//            switch (direction)
//            {
//                case ItemTouchHelper.LEFT:
//                    deletedmap=arrayList.get(position);
//                    deleted=deletedmap.get("name");
//                    arrayList.remove(position);
//                    adb.deleteContact(pos);
//                    adapter.notifyItemRemoved(position);
//                    Snackbar.make(recyclerView, (CharSequence) deleted, BaseTransientBottomBar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            int position = 0;
//                            arrayList.add(position,deletedmap);
//                            adapter.notifyItemInserted(position);
//                        }
//                    }).show();
//                    recyclerView.setAdapter(adapter);
//                    break;
//
//                case ItemTouchHelper.RIGHT:
//                    Intent i=new Intent(RecyclerEx.this,MainActivity.class).putExtra("id",pos);
//                    startActivity(i);
//                    break;
//            }
//        }

}