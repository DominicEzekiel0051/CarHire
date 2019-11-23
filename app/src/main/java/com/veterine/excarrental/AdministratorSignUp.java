package com.veterine.excarrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AdministratorSignUp extends AppCompatActivity {
    private Toolbar toolbar;
    private Button button;
    Boolean CheckEditText;
    private EditText username, email, password;
    private String url="http://192.168.43.240/excarental/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_sign_up);

        username = (EditText)findViewById(R.id.admin_user_name);
        email = (EditText)findViewById(R.id.admin_email);
        password = findViewById(R.id.admin_password);
        button = findViewById(R.id.AdminSignUpButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextIsEmptyOrNot();
                if(CheckEditText){

                final String Name = username.getText().toString();
                final String Email = email.getText().toString();
                final String Password = password.getText().toString();
                final RequestQueue requestQueue = Volley.newRequestQueue(AdministratorSignUp.this);

                StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url, response -> {
                    response.trim();
                    Toast.makeText(AdministratorSignUp.this, response, Toast.LENGTH_SHORT).show();
                    if (response.equals("registered")) {
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    }

                },
                        error -> requestQueue.stop()
                ) {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("username", Name);
                        params.put("email", Email);
                        params.put("password", Password);
                        return params;
                    }

                };

                requestQueue.add(stringRequest);
            }
                else{
                    Toast.makeText(AdministratorSignUp.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }

            }

            public void CheckEditTextIsEmptyOrNot() {

                final String Username = username.getText().toString();
                final String Email = email.getText().toString();
                final String Password = password.getText().toString();


                if ( TextUtils.isEmpty(Username) || TextUtils.isEmpty(Password) || TextUtils.isEmpty(Email) ) {

                    CheckEditText = false;
                } else {

                    CheckEditText = true;
                }
            }

        });

    }
}

