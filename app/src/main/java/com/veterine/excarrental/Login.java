package com.veterine.excarrental;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText username, email, password;
    private Button submit, forgot_password;
    private Toolbar toolbar;

    String insertUrl = "http://192.168.43.240/excarental/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        //Referencing the variables from the xml file

        username = (EditText)findViewById(R.id.log_InUsername);
        password = (EditText)findViewById(R.id.log_InPassword);
        submit = (Button)findViewById(R.id.submit);
        forgot_password = (Button)findViewById(R.id.forgot_password);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Name = username.getText().toString();
                final String Password = password.getText().toString();
                final RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        response.trim();

                        if(response.equals("validUser")){

                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), AdministratorAccount.class);
                            startActivity(intent);
                            finish();
                        }
                        else {


                           Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();

                        }

                    }
                },
                        new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestQueue.stop();

                    }
                })

                {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("username", Name);
                        params.put("password", Password);
                        return params;
                    }
                };

                requestQueue.add(stringRequest);
            }


        });



    }



}

