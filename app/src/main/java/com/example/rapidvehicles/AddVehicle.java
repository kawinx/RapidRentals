package com.example.rapidvehicles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddVehicle extends AppCompatActivity {

    EditText modal,plate,passenger,type,price,vurl;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_add);

        modal = (EditText)findViewById(R.id.txtmodal);
        plate = (EditText)findViewById(R.id.txtPlate);
        passenger = (EditText)findViewById(R.id.txtpassenger);
        type = (EditText)findViewById(R.id.txtType);
        price = (EditText)findViewById(R.id.txtPrice);
        vurl = (EditText)findViewById(R.id.txtimageUrl);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("modal",modal.getText().toString());
        map.put("plate",plate.getText().toString());
        map.put("passenger",passenger.getText().toString());
        map.put("type",type.getText().toString());
        map.put("price",price.getText().toString());
        map.put("vurl",vurl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("vehicles").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddVehicle.this, "Data Inserted Successfully.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddVehicle.this, "Error While Insertion.", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearAll(){
        modal.setText("");
        plate.setText("");
        passenger.setText("");
        type.setText("");
        price.setText("");
        vurl.setText("");
    }
}