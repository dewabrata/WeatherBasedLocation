package com.juara.weatherbasedlocation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juara.weatherbasedlocation.R;
import com.juara.weatherbasedlocation.model.geolocation.Geolocation;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class AdapterListSimple extends RecyclerView.Adapter<AdapterListSimple.ViewHolder> {




    java.util.List<Geolocation> data;
    Context context;
    String lokasi;


    public AdapterListSimple(Context context, java.util.List<Geolocation> data){



        this.data = data;
        this.context = context;

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_kiri, parent, false);


        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        holder.txtLatitude.setText(data.get(position).getLatitude());
        holder.txtLongitude.setText(data.get(position).getLongitude());

        holder.txtTanggal.setText(data.get(position).getDatetime());

        String image = "http://dewabrata.com/dewa/uploads/geolocation/"+ data.get(position).getPhoto();
        Picasso.get().load(image).into(holder.image);







    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView txtLatitude,txtLongitude,txtTanggal;
        public ImageView image;
        public LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            txtLatitude = (TextView) itemView.findViewById(R.id.txtLatitude);
            txtLongitude = (TextView) itemView.findViewById(R.id.txtLongitude);

            txtTanggal = (TextView) itemView.findViewById(R.id.txtTanggal);

            image = (ImageView)itemView.findViewById(R.id.image);
            parentLayout = (LinearLayout)itemView.findViewById(R.id.parentLayout);

        }

    }


}
