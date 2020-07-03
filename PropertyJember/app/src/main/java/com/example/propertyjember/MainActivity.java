package com.example.propertyjember;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button mrumah, mtanah, mruko, mtransaksi, makun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mrumah = (Button) findViewById(R.id.tmblrumah);
        mtanah = (Button) findViewById(R.id.tmbltanah);
        mruko = (Button) findViewById(R.id.tmblruko);
        mtransaksi = (Button) findViewById(R.id.tmbltransaksi);
        makun = (Button) findViewById(R.id.tmblakun);

        mrumah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rumah = new Intent(getApplicationContext(), ListRumah.class);
                startActivity(rumah);
                finish();
            }
        });

        mtanah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tanah = new Intent(getApplicationContext(), ListTanah.class);
                startActivity(tanah);
                finish();
            }
        });

        mruko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ruko = new Intent(getApplicationContext(), ListRuko.class);
                startActivity(ruko);
                finish();
            }
        });

        mtransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transaksi = new Intent(getApplicationContext(), ListSewa.class);
                startActivity(transaksi);
                finish();
            }
        });

        makun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent akun = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(akun);
                finish();
            }
        });

    }
    }