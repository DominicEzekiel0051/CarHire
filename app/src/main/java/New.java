/**
public class New {

    public class Register extends AppCompatActivity {

        private Button btn;
        private EditText username;
        private EditText password;
        private EditText email;
        private String url = "http://192.168.43.168/mydawasa/register.php";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            username = findViewById(R.id.userName);
            email = findViewById(R.id.email);
            password = findViewById(R.id.password);

            btn = (Button) findViewById(R.id.registerBtn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String nm = username.getText().toString();
                    final String ps = password.getText().toString();
                    final String em = email.getText().toString();
                    final RequestQueue requestQueue = Volley.newRequestQueue(Register.this);
                    StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    response.trim();
                                    Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();
                                    if (response.equals("registered")) {
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
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
                            params.put("username", nm);
                            params.put("password", ps);
                            params.put("email", em);
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            });

        }
    }
}
 **/