package com.veterine.excarrental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
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

public class Db_SpList extends AppCompatActivity {


    TextView image_name;
    private static final String url = "http://192.168.43.240/excarental/fetch_sps.php";
    RecyclerView recyclerView;
    Db_SpListAdapter adapter;

    //a list to store all the products
    List<Db_SpListData> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db__sp_list);

        //initializing the productlist
        productList = new ArrayList<>();

        image_name = findViewById(R.id.image_name);

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadCars();


    }

    private void loadCars(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray cars = jsonObject.getJSONArray("cars");


                    for(int i=0; i<cars.length(); i++){

                        JSONObject carsObject = cars.getJSONObject(i);

                        int id = carsObject.getInt("id");
                        String pass_no = carsObject.getString("pass_no");
                        String image_name = carsObject.getString("image_name");
                        String image_path = carsObject.getString("image_path");
                        String price = carsObject.getString("price");

                        Db_SpListData db_carsListRecyclerViewData = new Db_SpListData(id, image_name, pass_no, price,  image_path);
                        productList.add(db_carsListRecyclerViewData);

                       // Toast.makeText(Db_SpList.this, response, Toast.LENGTH_LONG).show();

                    }


                    //creating recyclerview adapter
                    adapter = new Db_SpListAdapter(Db_SpList.this, productList);

                    // setting adapter to recyclerview
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Db_SpList.this,error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);


    }
}
