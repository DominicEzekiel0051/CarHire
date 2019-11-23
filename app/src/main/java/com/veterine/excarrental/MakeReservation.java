package com.veterine.excarrental;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MakeReservation extends AppCompatActivity {

    private Toolbar toolbar;
    double total;
    String RefNo;
    int count;
    Boolean CheckEditText;
    Boolean check;
    long number;

    private TextView picklocation, returnlocation, pickdate, returndate, numberOfdays;
    private Button make_reservation;
    private ImageView imageView;
    private String HttpUrl = "http://192.168.43.240/excarental/makereservation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);



        //imageView = (ImageView) findViewById(R.id.reservation_image);
        // imageView.setImageResource(getIntent().getIntExtra("ListViewClickValue", 00));
        picklocation = findViewById(R.id.picklocation);
        returnlocation = findViewById(R.id.returnplace);
        pickdate = findViewById(R.id.pickdate);
        returndate = findViewById(R.id.returndate);
        numberOfdays = findViewById(R.id.numberDays);
        make_reservation = findViewById(R.id.reserve);


        make_reservation.setOnClickListener(v -> {

            CheckEditTextIsEmptyOrNot();
            if (CheckEditText) {

                // final int NumberOfdays = numberOfdays.getText().charAt(5);

                total = 0.0;
                total = 7 * 220;

                final EditText txtRef = new EditText(this);

                // Starting Payment Process....
                txtRef.setHint("ReferenceNO: Eg 4E51LTDLWF");

                new AlertDialog.Builder(this)
                        .setTitle("COMPLETE PAYMENTS WITH M-Pesa")
                        .setMessage("Send " + total + " Tshs to M-Pesa number 0742462687 to pay for your order." + "\n" + "INSERT Here the Reference number you were sent from M-Pesa.")
                        .setView(txtRef)
                        .setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                RefNo = txtRef.getText().toString();


                                //getting the mpesacode from the database

                                // Initiating OKHttp
                                OkHttpClient client = new OkHttpClient();

                                okhttp3.Request request = new Request.Builder()
                                        .url("http://192.168.43.240/excarental/getCode.php")
                                        .build();

                                client.newCall(request).enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        try {
                                            String responseData = response.body().string();

                                            JSONObject json = new JSONObject(responseData);

                                            JSONArray mpesainfo = json.getJSONArray("mpesacode");

                                            for (int i = 0; i < mpesainfo.length(); i++) {
                                                JSONObject obj = mpesainfo.getJSONObject(i);

                                                if (obj.getString("code").equals(RefNo)) {

                                                    runOnUiThread(new Runnable() {
                                                        public void run() {

                                                            Toast.makeText(MakeReservation.this, "Payment Successful", Toast.LENGTH_LONG).show();

                                                            final String PickUpLocation = picklocation.getText().toString();
                                                            final String ReturnLocation = returnlocation.getText().toString();
                                                            final String PickUpDate = pickdate.getText().toString();
                                                            final String ReturnDate = returndate.getText().toString();

                                                            final RequestQueue requestQueue = Volley.newRequestQueue(MakeReservation.this);

                                                            StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, HttpUrl, response -> {
                                                                response.trim();

                                                                if (response.equals("Reservation Successful")) {
//                                                                Intent billIntent = new Intent(getApplicationContext(), Bill.class);
//                                                                startActivity(billIntent);

                                                                    Toast.makeText(MakeReservation.this, response, Toast.LENGTH_LONG).show();
                                                                }

                                                            },
                                                                    error -> requestQueue.stop()
                                                            ) {

                                                                @Override
                                                                protected Map<String, String> getParams() {
                                                                    Map<String, String> params = new HashMap<String, String>();
                                                                    params.put("picklocation", PickUpLocation);
                                                                    params.put("returnlocation", ReturnLocation);
                                                                    params.put("pickdate", PickUpDate);
                                                                    params.put("returndate", ReturnDate);

                                                                    return params;
                                                                }

                                                            };

                                                            requestQueue.add(stringRequest);


                                                        }
                                                    });


                                                    check = false;

                                                }


                                            }


                                        } catch (JSONException e) {

                                        }


                                    }
                                });


                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .show();
            }

            else{
                Toast.makeText(MakeReservation.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
            }
        });

    }

            public void CheckEditTextIsEmptyOrNot(){
                final String PickLocation = picklocation.getText().toString();
                final String ReturnLocation = returnlocation.getText().toString();
                final String PickDate= pickdate.getText().toString();
                final String ReturnDate= returndate.getText().toString();


                if (TextUtils.isEmpty(PickLocation) || TextUtils.isEmpty(ReturnLocation) ||TextUtils.isEmpty(PickDate) || TextUtils.isEmpty(ReturnDate)) {

                    CheckEditText = false;
                } else {

                    CheckEditText = true;
                }
            }
        }





