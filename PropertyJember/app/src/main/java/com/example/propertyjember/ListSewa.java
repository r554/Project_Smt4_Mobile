package com.example.propertyjember;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListSewa extends BaseAdapter {
    private Context context;
    private ArrayList<DataInfosewa> datasewaArrayList;
    public ListSewa(Context context, ArrayList<DataInfosewa>
            datasewaArrayList) {
        this.context = context;
        this.datasewaArrayList = datasewaArrayList;
    }
    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return datasewaArrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return datasewaArrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup
            parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context

                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_listinfo,
                    null, true);
            holder.nama = (TextView)
                    convertView.findViewById(R.id.jenis);
            holder.harga = (TextView)
                    convertView.findViewById(R.id.harga);
            holder.alamat = (TextView)
                    convertView.findViewById(R.id.alamat);
            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.nama.setText("Jenis "+datasewaArrayList.get(position).getHarga());
        holder.harga.setText("Harga "+datasewaArrayList.get(position).getAlamat());
        holder.alamat.setText("Alamat "+datasewaArrayList.get(position).getNama());
        return convertView;
    }
    private class ViewHolder {
        protected TextView nama, harga, alamat;
    }
}
