package com.example.propertyjember;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Infosewa extends AppCompatActivity {

    private String URLstring =
            "http://localhost/propertylogin//Get_Data.php";
    private static ProgressDialog mProgressDialog;
    private ListView listView;
    ArrayList<DataInfosewa> datasewaArrayList;
    private ListSewa listSewa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.nik);
        retrieveJSON();
    }

    private void retrieveJSON() {
        showSimpleProgressDialog(this, "Loading...","Fetching Json",false);
        StringRequest stringRequest = new
                StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("status").equals("true")){
                                datasewaArrayList = new ArrayList<>();
                                JSONArray dataArray =
                                        obj.getJSONArray("data");
                                for (int i = 0; i < dataArray.length();
                                     i++) {
                                    DataInfosewa dataModel = new
                                            DataInfosewa();
                                    JSONObject dataobj =
                                            dataArray.getJSONObject(i);

                                    dataModel.setNama(dataobj.getString("jenis"));

                                    dataModel.setHarga(dataobj.getString("harga"));

                                    dataModel.setAlamat(dataobj.getString("alamat"));

                                    datasewaArrayList.add(dataModel);
                                }
                                setupListview();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void setupListview(){
        removeSimpleProgressDialog(); //will remove progress dialog
        listSewa = new ListSewa(this, datasewaArrayList);
        listView.setAdapter(listSewa);
    }
    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showSimpleProgressDialog(Context context,
                                                String title,
                                                String msg,
                                                boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context,
                        title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

