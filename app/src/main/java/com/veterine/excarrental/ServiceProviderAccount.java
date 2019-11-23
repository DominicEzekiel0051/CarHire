package com.veterine.excarrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ServiceProviderAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_account);
    }

    public void openDb_CarsListRecyclerView(View view) {
        Intent intent = new Intent(this, Db_CarsListRecyclerView.class);
        startActivity(intent);
    }

    public void openDb_UploadCars(View view) {
        Intent intent1 = new Intent(this, Db_UploadCars.class);
        startActivity(intent1);
    }
}
