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

public class ServiceProviderSignUp extends AppCompatActivity {
    private TextView companyname, companyregno, companylocation, email, password, phone;
    private Button register;
    Boolean CheckEditText;
    private String url = "http://192.168.43.240/excarental/service_provider_register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_sign_up);

        companyname = findViewById(R.id.company_name);
        companyregno = findViewById(R.id.company_reg_no);
        companylocation = findViewById(R.id.company_location);
        email = findViewById(R.id.company_email);
        password = findViewById(R.id.company_password);
        phone = findViewById(R.id.company_phone);
        register = findViewById(R.id.ServiceProviderButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckEditTextIsEmptyOrNot();
                if(CheckEditText){
                final String CompanyName = companyname.getText().toString();
                final String CompanyReg_No = companyregno.getText().toString();
                final String CompanyLocation = companylocation.getText().toString();
                final String CompanyEmail = email.getText().toString();
                final String Password = password.getText().toString();
                final String CompanyPhone = phone.getText().toString();
                final RequestQueue requestQueue = Volley.newRequestQueue(ServiceProviderSignUp.this);

                StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response.trim();
                        Toast.makeText(ServiceProviderSignUp.this, response, Toast.LENGTH_LONG).show();
                        if (response.equals("registered")) {
                            Intent intent = new Intent(getApplicationContext(), ServiceProviderLogin.class);
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
                    protected Map<String, String> getParams(){
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("companyname", CompanyName);
                        params.put("companyregno", CompanyReg_No);
                        params.put("companylocation", CompanyLocation);
                        params.put("email", CompanyEmail);
                        params.put("password", Password);
                        params.put("phone", CompanyPhone);
                        return params;
                    }

                };

                requestQueue.add(stringRequest);
            }
                else{
                    Toast.makeText(ServiceProviderSignUp.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }

            }

            public void CheckEditTextIsEmptyOrNot() {
                final String CompanyName = companyname.getText().toString();
                final String CompanyReg = companyregno.getText().toString();
                final String CompanyLocation = companylocation.getText().toString();
                final String Email = email.getText().toString();
                final String Password = password.getText().toString();
                final String PhoneNumber = phone.getText().toString();


                if (TextUtils.isEmpty(CompanyName) || TextUtils.isEmpty(CompanyReg) || TextUtils.isEmpty(CompanyLocation)   || TextUtils.isEmpty(Password) || TextUtils.isEmpty(PhoneNumber)|| TextUtils.isEmpty(Email) ) {

                    CheckEditText = false;
                } else {

                    CheckEditText = true;
                }
            }

        });

    }
}

