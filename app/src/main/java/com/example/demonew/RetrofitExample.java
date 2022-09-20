package com.example.demonew;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitExample  extends AppCompatActivity {
    TextView retro, dateformat;
    String dateAdded,dateModified;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_example);
        retro=findViewById(R.id.retrofit);
        dateformat=findViewById(R.id.dateformat);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.quotable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<ModalClass> call = retrofitAPI.getPosts();

        call.enqueue(new Callback<ModalClass>() {
            @Override
            public void onResponse(Call<ModalClass> call, Response<ModalClass> response) {
                if(!response.isSuccessful()) {
                    retro.setText("Code: " +response.code());
                    return;
                }
                String data="";
                data+="ID: "+ response.body().get_id() +"\n";
                data+="Tag: "+ response.body().getTags()+"\n";
                data+="Content: "+ response.body().getContent()+"\n";
                data+="Author: "+ response.body().getAuthor()+"\n";
                data+="AuthorSlug: "+ response.body().getAuthorSlug()+"\n";
                data+="Length: "+ response.body().getLength()+"\n";
                data+="DateAdded: "+ response.body().getDateAdded()+"\n";
                data+="DateModified: "+ response.body().getDateModified()+"\n";
                retro.append(data);

                dateAdded=response.body().getDateAdded();

                try {
                    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                    Date d=dateFormat.parse(dateAdded);
                    SimpleDateFormat Dformat=new SimpleDateFormat("dd-MM-yyyy");
                    dateAdded=Dformat.format(d);
                    Log.e("dateAdded!","==>"+dateAdded);
                } catch (ParseException e) {
                    Log.e("Catching!","seee==>"+e.getMessage());
                    e.printStackTrace();
                }
                dateformat.setText(dateAdded);
                dateModified=response.body().getDateModified();
            }

            @Override
            public void onFailure(Call<ModalClass> call, Throwable t) {
                retro.setText(t.getMessage());
            }
        });
    }
}
