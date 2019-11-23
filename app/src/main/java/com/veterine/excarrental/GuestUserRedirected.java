package com.veterine.excarrental;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class GuestUserRedirected extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_user_redirected);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

    }

    public void openCustomerSignUp(View view) {
        Intent customer_Intent = new Intent(this, CustomerSignUp.class);
        startActivity(customer_Intent);

    }

    public void openLogin(View view) {
        Intent customer_Intent = new Intent(this, Login.class);
        startActivity(customer_Intent);

    }
}
