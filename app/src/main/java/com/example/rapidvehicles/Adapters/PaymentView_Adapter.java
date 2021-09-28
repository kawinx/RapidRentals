package com.example.rapidvehicles.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rapidvehicles.Models.Payments;
import com.example.rapidvehicles.R;

import java.util.ArrayList;

public class PaymentView_Adapter extends RecyclerView.Adapter<PaymentView_Adapter.ViewHolder> {

    private Context context;
    private ArrayList<Payments> paymentsArrayList;
    private onItemClickListner mlistner;

    public PaymentView_Adapter(Context context, ArrayList<Payments> paymentsArrayList) {
        this.context = context;
        this.paymentsArrayList = paymentsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_paymentview,parent,false);
        return new PaymentView_Adapter.ViewHolder(view,mlistner);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Payments data = paymentsArrayList.get(position);
        holder.vname.setText(data.getVtype());
        holder.total.setText(data.getTotal());
        holder.date.setText(data.getPdate());
        holder.numdays.setText(data.getNum_rent_day());

    }

    @Override
    public int getItemCount() {
        return paymentsArrayList.size();
    }

    public void updateData(ArrayList<Payments> paymentsArrayList1){
        paymentsArrayList.clear();
        paymentsArrayList.addAll(paymentsArrayList1);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView vname,total,date,numdays;
        ImageView delete;


        public ViewHolder(@NonNull View itemView,onItemClickListner itemClickListner) {
            super(itemView);

            delete = itemView.findViewById(R.id.delete);
            vname  = itemView.findViewById(R.id.ptype);
            total = itemView.findViewById(R.id.ptotal);
            date = itemView.findViewById(R.id.pdate);
            numdays = itemView.findViewById(R.id.pdays);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mlistner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mlistner.onDelete(position);

                        }
                    }
                }
            });
        }
    }

    public interface onItemClickListner{
        void onDelete(int position);
    }
    public void setOnItemClickListner(onItemClickListner listner){
        mlistner = listner;
    }
}
