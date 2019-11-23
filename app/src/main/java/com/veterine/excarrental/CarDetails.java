package com.veterine.excarrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import static com.veterine.excarrental.Db_CarsListRecyclerView.ImageName;
import static com.veterine.excarrental.Db_CarsListRecyclerView.ImagePath;
import static com.veterine.excarrental.Db_CarsListRecyclerView.PassNumb;
import static com.veterine.excarrental.Db_CarsListRecyclerView.Price;


public class CarDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        Intent intent = getIntent();
        String image_path = intent.getStringExtra(ImagePath);
        String image_name = intent.getStringExtra(ImageName);
        String image_price = intent.getStringExtra(Price);
        String image_description =  intent.getStringExtra(PassNumb);

        ImageView imageView = findViewById(R.id.imageView4);
        TextView textView = findViewById(R.id.textView4);
        TextView textView2 = findViewById(R.id.textView6);
        TextView textView3 = findViewById(R.id.textView8);

        Glide.with(this)
                .load(image_path)
                .fitCenter()
                .centerCrop()
                .into(imageView);
        textView.setText(image_name);
        textView2.setText(image_description);
        textView3.setText(image_price);

    }

    public void reserve(View view) {
        Intent intent = new Intent(CarDetails.this, MakeReservation.class);
        startActivity(intent);
    }
}
