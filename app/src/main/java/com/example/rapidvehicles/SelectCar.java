package com.example.rapidvehicles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.rapidvehicles.Adapters.Vehicle_Adapter;
import com.example.rapidvehicles.Models.Vehicles;

import java.util.ArrayList;
import java.util.Calendar;

public class SelectCar extends AppCompatActivity {

    TextView startdate,enddate;
    DatePickerDialog.OnDateSetListener setListener;
    private int btnid;
    private Vehicle_Adapter adapter;
    int year;
    int day;
    int month;
    String stdate,endate;
    RecyclerView vehicle_recycler;
    String cloc,enloc,stloc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_car);

        startdate = findViewById(R.id.startdate);
        enddate = findViewById(R.id.enddate);
        vehicle_recycler = findViewById(R.id.vehicle_recycler);
        vehicle_recycler.setHasFixedSize(true);
        vehicle_recycler.setLayoutManager(new LinearLayoutManager(this));

        Calendar calendar = Calendar.getInstance();
         year = calendar.get(Calendar.YEAR);
         month = calendar.get(Calendar.MONTH);
         day = calendar.get(Calendar.DAY_OF_MONTH);
         GetData();
         setVehicles();
        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnid = 1;
                SelectDate();

            }
        });

        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnid = 2;
                SelectDate();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                if(btnid == 1){
                    i1 = i1+1;
                    stdate = i+"/"+i1+"/"+i2;
                    startdate.setText(stdate);
                }
                else if(btnid == 2){
                    i1 = i1+1;
                    endate = i+"/"+i1+"/"+i2;
                    enddate.setText(endate);
                }

            }
        };
    }

    private void SelectDate(){

        DatePickerDialog dialog = new DatePickerDialog(SelectCar.this, android.R.style.Theme,setListener,year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void GetData(){

        Intent i = getIntent();
        stloc = i.getStringExtra("stloc");
        enloc = i.getStringExtra("enlock");
        cloc = i.getStringExtra("cloc");
    }

    private ArrayList<Vehicles> setVehicles() {

        ArrayList<Vehicles> vehiclesArrayList = new ArrayList<>();
        vehiclesArrayList.add(new Vehicles(1, R.drawable.kdh, "KDH","4.5","7",5000.0f));
        vehiclesArrayList.add(new Vehicles(2, R.drawable.vitz, "Toyota Vitz","4.0","5",2500.0f));
        vehiclesArrayList.add(new Vehicles(3, R.drawable.premio, "Toyota Premio","4.7","5",3500.0f));
        vehiclesArrayList.add(new Vehicles(4, R.drawable.tuktuk, "Tuk Tuk","3.5","3",1000.0f));

        adapter = new Vehicle_Adapter(vehiclesArrayList,this);
        vehicle_recycler.setAdapter(adapter);

        adapter.setOnItemClickListner(new Vehicle_Adapter.onItemClickListner() {
            @Override
            public void onClick(int position) {
                Vehicles data = vehiclesArrayList.get(position);
                Intent i = new Intent(SelectCar.this,BookingConfirm.class);
                i.putExtra("vname",data.getVname());
                i.putExtra("vimage",data.getImage());
                i.putExtra("price",data.getPrice());
                i.putExtra("psg",data.getPassangers());
                i.putExtra("cloc",cloc);
                i.putExtra("stdate",startdate.getText().toString());
                i.putExtra("endate",enddate.getText().toString());
                i.putExtra("stloc",stloc);
                i.putExtra("enloc",enloc);
                startActivity(i);
            }
        });

        return vehiclesArrayList;

    }
}