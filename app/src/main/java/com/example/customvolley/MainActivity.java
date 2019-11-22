package com.example.customvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url="http://www.json-generator.com/api/json/get/ccLAsEcOSq?indent=2";
    List<DataModel>list=new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.lv);

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object=new JSONObject(response);
                    JSONArray array=object.getJSONArray("book_array");

                    for (int i=0;i<=array.length();i++){
                        JSONObject object1=array.getJSONObject(i);

                        String book_title=object1.getString("book_title");
                        String imageUrl=object1.getString("image");
                        String author=object1.getString("author");

                        DataModel model=new DataModel();
                        model.setBook_title(book_title);
                        model.setImageUrl(imageUrl);
                        model.setAuthor(author);

                        list.add(model);

                        Base_Adapter adapter=new Base_Adapter(MainActivity.this,list);
                        listView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        queue.add(request);
    }
}
