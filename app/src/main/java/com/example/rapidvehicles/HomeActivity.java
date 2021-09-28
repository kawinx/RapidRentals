package com.example.rapidvehicles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    AppCompatButton btnfind,btnview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnfind = findViewById(R.id.find);
        btnview = findViewById(R.id.pview);


        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i  = new Intent(HomeActivity.this,ViewPayments.class);
                startActivity(i);
            }
        });

        btnfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}