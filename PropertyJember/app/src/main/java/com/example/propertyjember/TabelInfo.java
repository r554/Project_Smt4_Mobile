package com.example.propertyjember;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

public class TabelInfo extends AppCompatActivity {
    TextView notransaksi, tglpembayaran, harga, status;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    String HttpUrl = "http://localhost/propertylogin//Get_Data.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listinfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        TableLayout tablelayoutid = (TableLayout)this.findViewById(R.id.tablelayoutid);
        // Inflate your row "template" and fill out the fields.
        TableRow row = (TableRow)getLayoutInflater().inflate(R.layout.activity_listinfo, null);
        ((TextView)row.findViewById(R.id.notransaksi)).setText("NO");
        //((TextView)row.findViewById(R.id.tglpembayaran)).setText("Tanggal Pembayaran");
        ((TextView)row.findViewById(R.id.harga)).setText("Harga");
        ((TextView)row.findViewById(R.id.status)).setText("Status");
        tablelayoutid.addView(row);
        return true;
    }


}
