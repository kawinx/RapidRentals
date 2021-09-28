package com.project.rapidrentals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends AppCompatActivity {

    TextView username,email,password,nic,phoneNumber;
    Button edit,delete;

    DatabaseReference profiledeleteref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

    username = findViewById(R.id.profUserName);
    email = findViewById(R.id.profEmail);
    password = findViewById(R.id.profPassword);
    nic = findViewById(R.id.profNic);
    phoneNumber = findViewById(R.id.profPhoneNumber);
    edit = findViewById(R.id.editprfl);
    delete = findViewById(R.id.deleteprfl);


        Intent prflIntent = getIntent();

        String UserName = prflIntent.getStringExtra("userName");
        String Email = prflIntent.getStringExtra("email");
        String Password = prflIntent.getStringExtra("password");
        String NIC =prflIntent.getStringExtra("NIC");
        String PhoneNumber =prflIntent.getStringExtra("PhoneNumber");


        username.setText(UserName);
        email.setText(Email);
        password.setText(Password);
        nic.setText(NIC);
        phoneNumber.setText(PhoneNumber);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(profile.this,EditProfile.class);
                editIntent.putExtra("userName",UserName);
                editIntent.putExtra("email",Email);
                editIntent.putExtra("password",Password);
                editIntent.putExtra("NIC",NIC);
                editIntent.putExtra("PhoneNumber",PhoneNumber);
                startActivity(editIntent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                profiledeleteref = FirebaseDatabase.getInstance().getReference("User").child(UserName);
                profiledeleteref.removeValue();

                Intent deleteprflintent = new Intent(profile.this, Login.class);
                startActivity(deleteprflintent);
                finish();

            }
        });
    }
}