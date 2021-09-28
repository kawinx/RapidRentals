package com.example.rapidvehicles.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rapidvehicles.Models.Vehicles;
import com.example.rapidvehicles.R;

import java.util.ArrayList;

public class Vehicle_Adapter extends RecyclerView.Adapter<Vehicle_Adapter.ViewHolder> {

    private ArrayList<Vehicles> vehiclesArrayList;
    private Context context;
    private onItemClickListner mlistner;

    public Vehicle_Adapter(ArrayList<Vehicles> vehiclesArrayList, Context context) {
        this.vehiclesArrayList = vehiclesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_vehicle,parent,false);
        return new Vehicle_Adapter.ViewHolder(view,mlistner);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Vehicles data = vehiclesArrayList.get(position);
        holder.p_no.setText(data.getPassangers());
        holder.ratings.setText(data.getRatings());
        holder.v_name.setText(data.getVname());

        Glide.with(context)
                .load(data.getImage())
                .fitCenter()
                .into(holder.imageview);

    }

    @Override
    public int getItemCount() {
        return vehiclesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageview;
        TextView p_no,ratings,v_name;
        AppCompatButton fincar_btn;

        public ViewHolder(@NonNull View itemView,onItemClickListner itemClickListner) {
            super(itemView);

            imageview = itemView.findViewById(R.id.img_vehicle);
            p_no = itemView.findViewById(R.id.p_no);
            ratings = itemView.findViewById(R.id.ratings);
            v_name = itemView.findViewById(R.id.v_name);
            fincar_btn = itemView.findViewById(R.id.fincar_btn);

            fincar_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mlistner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mlistner.onClick(position);

                        }
                    }
                }
            });


        }
    }

    public interface onItemClickListner{
        void onClick(int position);
    }
    public void setOnItemClickListner(onItemClickListner listner){
        mlistner = listner;
    }
}
