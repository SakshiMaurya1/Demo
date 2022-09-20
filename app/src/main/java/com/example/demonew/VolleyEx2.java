package com.example.demonew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

public class VolleyEx2 extends AppCompatActivity {
    List<Modal2> vl2;
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_ex2);
        listview=findViewById(R.id.volleylst2);
        vl2=new ArrayList<>();
        gethere();
    }

    public void gethere(){
        RequestQueue queue = Volley.newRequestQueue(VolleyEx2.this);
        String url="https://api.quotable.io/tags";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject responseObj = response.getJSONObject(i);
                        String id = "ID: " + responseObj.getString("_id");
                        String name = "Name: " + responseObj.getString("name");
                        String dateadded = "Date added: " + responseObj.getString("dateAdded");
                        String datemod = "Date modified: " + responseObj.getString("dateModified");
                        String v = "v: " + responseObj.getString("__v");
                        String quotecount = "Quote count: " + responseObj.getString("quoteCount");
                        Modal2 m2 = new Modal2(id, name, dateadded, datemod, v, quotecount);
                        vl2.add(m2);
                    }
                    V2Adapter adapter=new V2Adapter(vl2,getApplicationContext());
                    listview.setAdapter(adapter);
                    Log.e("JSONDATA", "CHECKING222+++>>");
                } catch (JSONException e) {
                    Log.e("TAG", "onResponse: "  + e.toString());
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyEx2.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }
}