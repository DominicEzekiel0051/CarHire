package com.veterine.excarrental;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LogInOptions extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_options);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

    }

    public void openLogin(View view) {
        Intent intent1 = new Intent(this, Login.class);
        startActivity(intent1);
    }

    public void openServiceProviderLogin(View view) {
        Intent intent1 = new Intent(this, ServiceProviderLogin.class);
        startActivity(intent1);
    }

    public void openCustomerLogin(View view) {
        Intent intent1 = new Intent(this, CustomerLogin.class);
        startActivity(intent1);
    }
}
