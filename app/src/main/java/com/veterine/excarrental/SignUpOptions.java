package com.veterine.excarrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class SignUpOptions extends AppCompatActivity {

    private Toolbar toolbar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_options);

        //initialize the toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void openAdministratorSignUp(View view) {

        Toast.makeText(SignUpOptions.this, "For Authorised Users Only", Toast.LENGTH_LONG).show();
//        Intent adminIntent = new Intent(this, AdministratorSignUp.class);
//        startActivity(adminIntent);

    }

    public void openCustomerSignUp(View view) {
        Intent customer_Intent = new Intent(this, CustomerSignUp.class);
        startActivity(customer_Intent);

    }

    public void openServiceProviderSignUp(View view) {
        Intent service_provider_Intent = new Intent(this, ServiceProviderSignUp.class);
        startActivity(service_provider_Intent);


    }

}
