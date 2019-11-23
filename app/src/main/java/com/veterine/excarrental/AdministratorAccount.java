package com.veterine.excarrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdministratorAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_account);
    }

    public void openDb_CarsListRecyclerView(View view) {
        Intent intent = new Intent(this, Db_CarsListRecyclerView.class);
        startActivity(intent);
    }

    public void openDb_UploadCars(View view) {
        Intent intent1 = new Intent(this, Db_UploadCars.class);
        startActivity(intent1);
    }

    public void openDb_SpList(View view) {
        Intent intent2 = new Intent(this, Db_SpList.class);
        startActivity(intent2);
    }

    public void openDb_UploadSps(View view) {
        Intent intent3 = new Intent(this, Db_UploadSps.class);
        startActivity(intent3);
    }
}
