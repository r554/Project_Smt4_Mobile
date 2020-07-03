package com.example.propertyjember;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    // Creating EditText.
    EditText username, password ;
    SessionManager sessionManager;
    //Creating TextView
    TextView Register;

    // Creating button;
    Button LoginButton;

    // Creating Volley RequestQueue.
    RequestQueue requestQueue;

    // Create string variable to hold the EditText Value.
    String UsernameHolder, PasswordHolder ;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Storing server url into String variable.
    //public static String URL = ServerApi.URL_LOGIN;

    Boolean CheckEditText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.EditText_Username);

        password = (EditText) findViewById(R.id.EditText_Password);
        sessionManager = new SessionManager(getBaseContext());
        Register = (TextView) findViewById(R.id.textview_register);
        // Assigning ID's to Button.
        LoginButton = (Button) findViewById(R.id.button_login);

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(Login.this);

        // Assigning Activity this to progress dialog.
        progressDialog = new ProgressDialog(Login.this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login.this, com.example.propertyjember.Register.class);
                startActivity(registerIntent);
            }
        });

        // Adding click listener to button.
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(CheckEditText){

                    UserLogin();

                }
                else {

                    Toast.makeText(Login.this, "Data Tidak Boleh Kosong", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    public void UserLogin(){
        StringRequest senddata = new StringRequest(Request.Method.POST, ServerApi.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");
                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String nama_pengguna = jsonObject1.getString("nama_pengguna").trim();
                            String NIK_pengguna = jsonObject1.getString("NIK_pengguna").trim();
                            String alamat_pengguna = jsonObject1.getString("alamat_pengguna").trim();
                            String no_pengguna = jsonObject1.getString("no_pengguna");
                            String email = jsonObject1.getString("email").trim();
                            String foto_ktp = jsonObject1.getString("foto_ktp").trim();
                            String foto_diri_dan_ktp = jsonObject1.getString("foto_diri_dan_ktp").trim();
                            String foto_profil = jsonObject1.getString("foto_profil").trim();
                            String role = jsonObject1.getString("role").trim();

                            sessionManager.createSession(NIK_pengguna, nama_pengguna, alamat_pengguna,
                                    no_pengguna, email, foto_ktp,
                                    foto_diri_dan_ktp, foto_profil,  username.getText().toString(), password.getText().toString(), role);

                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(Login.this, "Data Kosong! ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Login.this, "Error login : " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "Error login : " + error.toString(), Toast.LENGTH_SHORT) .show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username.getText().toString());
                // sesuaikan dengan $_POST pada PHP
                params.put("password", password.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(senddata);
    }
}