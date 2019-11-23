
package com.veterine.excarrental;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeCarsList extends AppCompatActivity implements HomeCarsListAdapter.OnItemClickListener {

    public static final String ImagePath = "image_path";
    public static final String ImageName = "image_name";
    public static final String PassNumb = "pass_no";
    public static final String Price = "price";
    private Toolbar toolbar;

    TextView image_name;
    private static final String url = "http://192.168.43.240/excarental/fetch_cars.php";
    RecyclerView recyclerView;
    HomeCarsListAdapter adapter;

    //a list to store all the products
    List<HomeCarsListData> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cars_list);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);


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

                        //int id = carsObject.getInt("id");
                        String pass_no = carsObject.getString("pass_no");
                        String image_name = carsObject.getString("image_name");
                        String image_path = carsObject.getString("image_path");
                        String price = carsObject.getString("price");

                        HomeCarsListData db_carsListRecyclerViewData = new HomeCarsListData(image_name, pass_no, price,  image_path);
                        productList.add(db_carsListRecyclerViewData);

                        //  Toast.makeText(Db_CarsListRecyclerView.this, response, Toast.LENGTH_LONG).show();

                    }
                    adapter = new HomeCarsListAdapter(com.veterine.excarrental.HomeCarsList.this, productList);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(com.veterine.excarrental.HomeCarsList.this);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(com.veterine.excarrental.HomeCarsList.this,error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);


    }



    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this, HomeCarDetails.class);
        HomeCarsListData carItem = productList.get(position);

        // Toast.makeText(Db_CarsListRecyclerView.this, (CharSequence) carItem, Toast.LENGTH_LONG).show();


        intent.putExtra(ImagePath, carItem.getImage_path());
        intent.putExtra(ImageName, carItem.getImage_name());
        intent.putExtra(PassNumb, carItem.getPass_no());
        intent.putExtra(Price, carItem.getPrice());

        startActivity(intent);

    }
}
