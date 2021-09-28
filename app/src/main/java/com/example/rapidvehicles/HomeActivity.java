package com.example.rapidvehicles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class HomeActivity extends AppCompatActivity {

    AppCompatButton btnfind,btnview,feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnfind = findViewById(R.id.find);
        btnview = findViewById(R.id.pview);
        feed =    findViewById(R.id.feed);


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

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Coming();
            }
        });
    }

    private void Coming(){
        SweetAlertDialog pdialog = new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.WARNING_TYPE);
        pdialog.setTitleText("Soon");
        pdialog.setContentText("Coming Soon!");
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