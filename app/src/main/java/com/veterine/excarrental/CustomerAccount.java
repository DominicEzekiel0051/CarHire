package com.veterine.excarrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CustomerAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account);
    }

    public void openCustomerCarsList(View view) {
        Intent intent = new Intent(this, CustomerCarsList.class);
        startActivity(intent);
    }

    public void openDb_CarsListRecyclerView(View view) {
        Intent intent1 = new Intent(this, Db_CarsListRecyclerView.class);
        startActivity(intent1);
    }
}
