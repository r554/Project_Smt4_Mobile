package com.example.propertyjember;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    // Creating EditText.
    EditText Username, Password ;

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
    String HttpUrl = "http://192.168.1.106/propertylogin/User_Login.php";

    Boolean CheckEditText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = (EditText) findViewById(R.id.EditText_Username);

        Password = (EditText) findViewById(R.id.EditText_Password);
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

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    UserLogin();

                }
                else {

                    Toast.makeText(Login.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    public void UserLogin(){

        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String ServerResponse) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                        if (ServerResponse.equalsIgnoreCase("Data Matched")) {
                            Toast.makeText(Login.this, "Logged In Successfuly", Toast.LENGTH_LONG).show();
                            finish();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.putExtra("UsernameTAG", UsernameHolder);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this, ServerResponse, Toast.LENGTH_SHORT).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Showing error message if something goes wrong.
                        Toast.makeText(Login.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("username", UsernameHolder);
                params.put("password", PasswordHolder);

                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }


    public void CheckEditTextIsEmptyOrNot(){

        // Getting values from EditText.
        UsernameHolder = Username.getText().toString().trim();
        PasswordHolder = Password.getText().toString().trim();

        // Checking whether EditText value is empty or not.
        if(TextUtils.isEmpty(UsernameHolder) || TextUtils.isEmpty(PasswordHolder))
        {

            // If any of EditText is empty then set variable value as False.
            CheckEditText = false;

        }
        else {

            // If any of EditText is filled then set variable value as True.
            CheckEditText = true ;
        }
    }
}