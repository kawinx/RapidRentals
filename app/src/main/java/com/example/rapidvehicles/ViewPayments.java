package com.example.rapidvehicles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.rapidvehicles.Adapters.PaymentView_Adapter;
import com.example.rapidvehicles.Models.Payments;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ViewPayments extends AppCompatActivity {

    RecyclerView payment_recycler;
    DatabaseReference databaseReference;
    ArrayList<Payments> paymentsArrayList;
    ArrayList<String> keylist;
    ProgressBar progress_circular;
    PaymentView_Adapter adapter;
    ImageView backhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_payments);

        progress_circular = findViewById(R.id.progress_circular);
        payment_recycler = findViewById(R.id.payment_recycler);
        backhome = findViewById(R.id.backhome);
        paymentsArrayList = new ArrayList<>();
        keylist = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Payments");

        DataLoad();

        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewPayments.this,HomeActivity.class);
                startActivity(i);
            }
        });

    }

    private void DataLoad(){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    progress_circular.setVisibility(View.VISIBLE);
                    Payments payments = data.getValue(Payments.class);
                    paymentsArrayList.add(payments); // user details added to user list
                    keylist.add(data.getKey()); //adding keys to different list - path key
                }
                putDataIntoBottomRecyclerView(ViewPayments.this, paymentsArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewPayments.this, "Something Error Try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void putDataIntoBottomRecyclerView(Context context, ArrayList<Payments> itemsList) {

        Log.e("Listsize_check", "" + itemsList.size());
        if (itemsList.size() == 0){
            Toast.makeText(context, "No Data to Display", Toast.LENGTH_SHORT).show();
        }
        else {
            progress_circular.setVisibility(View.GONE);
            adapter = new PaymentView_Adapter(this, itemsList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            payment_recycler.setLayoutManager(layoutManager);
            payment_recycler.setAdapter(adapter);

            adapter.setOnItemClickListner(new PaymentView_Adapter.onItemClickListner() {
                @Override
                public void onDelete(int position) {
                    Payments dta = itemsList.get(position);
                    String key = keylist.get(position);
                    //Log.e("checkpostion",""+dta.getPname());
                    Log.e("CheckKey",""+key);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Payments").child(key);
                    databaseReference.removeValue();
                    DeleteSuccess();
                    adapter.updateData(itemsList);
                }
            });
        }
    }

    private void DeleteSuccess() {
        SweetAlertDialog pdialog = new SweetAlertDialog(ViewPayments.this, SweetAlertDialog.SUCCESS_TYPE);
        pdialog.setTitleText("Completed");
        pdialog.setContentText("Order Deleted Successfully");
        pdialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        });
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.show();
    }
}