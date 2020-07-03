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

public class Register extends AppCompatActivity {

    // Creating EditText.
    EditText NIK, NamaLengkap, Alamat, No, Email, Username, Password ;
    TextView Login;

    // Creating button;
    Button Register;

    // Creating Volley RequestQueue.
    RequestQueue requestQueue;

    // Create string variable to hold the EditText Value.
    String NIKHolder, NamaHolder, AlamatHolder, NoHolder, EmailHolder, UsernameHolder, PasswordHolder ;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Storing server url into String variable.

    Boolean CheckEditText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Assigning ID's to EditText.
        NIK = (EditText) findViewById(R.id.EditText_NIK);
        NamaLengkap = (EditText) findViewById(R.id.EditText_NamaLengkap);
        Alamat = (EditText) findViewById(R.id.EditText_Alamat);
        No = (EditText) findViewById(R.id.EditText_No);
        Email = (EditText) findViewById(R.id.EditText_Email);
        Username = (EditText) findViewById(R.id.EditText_Username);
        Password = (EditText) findViewById(R.id.EditText_Password);
        Login = (TextView) findViewById(R.id.textview_login);

        // Assigning ID's to Button.
        Register = (Button) findViewById(R.id.button_register);

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(Register.this);

        // Assigning Activity this to progress dialog.
        progressDialog = new ProgressDialog(Register.this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(Register.this, Login.class);
                startActivity(loginIntent);
            }
        });

        // Adding click listener to button.
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    UserRegistration();

                }
                else {

                    Toast.makeText(Register.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    public void UserRegistration(){

        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
        progressDialog.show();

        // Creating string request with post method.
        StringRequest senddata = new StringRequest(Request.Method.POST, ServerApi.URL_REGISTER,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String ServerResponse) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Showing Echo Response Message Coming From Server.
                        Toast.makeText(Register.this, "Register Success", Toast.LENGTH_LONG).show();
                        finish();
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Showing error message if something goes wrong.
                        Toast.makeText(Register.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("NIK_pengguna", NIKHolder);
                params.put("nama_pengguna", NamaHolder);
                params.put("alamat_pengguna", AlamatHolder);
                params.put("no_pengguna", NoHolder);
                params.put("email", EmailHolder);
                params.put("username", UsernameHolder);
                params.put("password", PasswordHolder);

                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(Register.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(senddata);

    }


    public void CheckEditTextIsEmptyOrNot(){

        // Getting values from EditText.
        NIKHolder = NIK.getText().toString().trim();
        NamaHolder = NamaLengkap.getText().toString().trim();
        AlamatHolder = Alamat.getText().toString().trim();
        NoHolder = No.getText().toString().trim();
        EmailHolder = Email.getText().toString().trim();
        UsernameHolder = Username.getText().toString().trim();
        PasswordHolder = Password.getText().toString().trim();

        // Checking whether EditText value is empty or not.
        if(TextUtils.isEmpty(NIKHolder) || TextUtils.isEmpty(NamaHolder) ||
                TextUtils.isEmpty(AlamatHolder) || TextUtils.isEmpty(NoHolder) || TextUtils.isEmpty(EmailHolder) ||
                TextUtils.isEmpty(UsernameHolder) || TextUtils.isEmpty(PasswordHolder))
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