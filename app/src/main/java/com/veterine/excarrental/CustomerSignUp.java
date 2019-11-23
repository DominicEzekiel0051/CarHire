package com.veterine.excarrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CustomerSignUp extends AppCompatActivity {
    private TextView fullname, nationality, nationalId, licence, username, email, phone, password;
    private Button register;
    Boolean CheckEditText;
    private String url="http://192.168.43.240/excarental/customer_register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_up);

        fullname = findViewById(R.id.customer_fullName);
        nationality = findViewById(R.id.customer_nationality);
        nationalId = findViewById(R.id.customer_nationalId);
        licence = findViewById(R.id.customer_licence);
        username = findViewById(R.id.customer_username);
        email = findViewById(R.id.customer_email);
        phone = findViewById(R.id.customer_phone);
        password = findViewById(R.id.customer_password);
        register = findViewById(R.id.CustomerSignUpButton);

        register.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick (View v){

                    CheckEditTextIsEmptyOrNot();
                    if(CheckEditText){

                final String UserFullName = fullname.getText().toString();
                final String UserNationality = nationality.getText().toString();
                final String UserNationalId = nationalId.getText().toString();
                final String UserLicence = licence.getText().toString();
                final String UserName = username.getText().toString();
                final String UserEmail = email.getText().toString();
                final String UserPassword = password.getText().toString();
                final String UserPhone = phone.getText().toString();
                final RequestQueue requestQueue = Volley.newRequestQueue(CustomerSignUp.this);

                StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response.trim();
                        Toast.makeText(CustomerSignUp.this, response, Toast.LENGTH_LONG).show();
                        if (response.equals("registered")) {
                            Intent intent = new Intent(getApplicationContext(), CustomerLogin.class);
                            startActivity(intent);
                        }

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                requestQueue.stop();
                            }
                        }
                ) {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("fullname", UserFullName);
                        params.put("nationality", UserNationality);
                        params.put("nationalId", UserNationalId);
                        params.put("licence", UserLicence);
                        params.put("username", UserName);
                        params.put("email", UserEmail);
                        params.put("password", UserPassword);
                        params.put("phone", UserPhone);
                        return params;
                    }

                };

                requestQueue.add(stringRequest);
            }

            else{
                Toast.makeText(CustomerSignUp.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
            }

        }

        public void CheckEditTextIsEmptyOrNot() {
            final String FullName = fullname.getText().toString();
            final String Nationality = nationality.getText().toString();
            final String NationalId = nationalId.getText().toString();
            final String License = licence.getText().toString();
            final String Username = username.getText().toString();
            final String Email = email.getText().toString();
            final String Password = password.getText().toString();
            final String PhoneNumber = phone.getText().toString();


            if (TextUtils.isEmpty(FullName) || TextUtils.isEmpty(Nationality) || TextUtils.isEmpty(NationalId) || TextUtils.isEmpty(License) || TextUtils.isEmpty(Username) || TextUtils.isEmpty(Password) || TextUtils.isEmpty(PhoneNumber)|| TextUtils.isEmpty(Email) ) {

                CheckEditText = false;
            } else {

                CheckEditText = true;
            }
        }

        });

    }
}

