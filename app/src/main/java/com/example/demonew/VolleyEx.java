package com.example.demonew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VolleyEx extends AppCompatActivity {
    ListView listView;
    List<VolleyModal> vlist;
    Button nextbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_ex);
        listView = (ListView) findViewById(R.id.volleylst);
        nextbtn=findViewById(R.id.nextbtn);
        vlist = new ArrayList<>();

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(VolleyEx.this,VolleyEx2.class);
                startActivity(intent);
            }
        });
        getData();


    }

    public void getData() {
        Log.e("TAG", "getData: " );
        RequestQueue queue = Volley.newRequestQueue(VolleyEx.this);
        String url="https://jsonplaceholder.typicode.com/posts";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.e("TAG", "onResponse: "  + response);
                try {
                for (int i = 0; i < response.length(); i++) {

                    JSONObject responseObj = response.getJSONObject(i);
                    String userID = "UserID: " + responseObj.getString("userId");
                    String id = "ID: " + responseObj.getString("id");
                    String title = "Title: " + responseObj.getString("title");
                    String body = "Body: " + responseObj.getString("body");
                    VolleyModal vmodal = new VolleyModal(userID, id, title, body);
                    vlist.add(vmodal);
                    }
                        VAdapter adapter=new VAdapter(vlist,getApplicationContext());
                        listView.setAdapter(adapter);
                        Log.e("JSONDATA", "CHECKING+++>>");
                    } catch (JSONException e) {
                        Log.e("TAG", "onResponse: "  + e.toString());
                        e.printStackTrace();
                    }
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyEx.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);

    }
}