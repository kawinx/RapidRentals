package com.project.rapidrentals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.rapidrentals.Models.User;

public class Register extends AppCompatActivity {

    EditText registerEmail,registerUsername,registerPassword,registerNIC,registerPhoneNumber;
    Button registerButton;
    DatabaseReference regReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerEmail = findViewById(R.id.regEmail);
        registerUsername = findViewById(R.id.regUserName);
        registerPassword = findViewById(R.id.regPassword);
        registerNIC = findViewById(R.id.regNic);
        registerPhoneNumber = findViewById(R.id.regPhoneNumber);

        registerButton = findViewById(R.id.register);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                regReference = FirebaseDatabase.getInstance().getReference("User");

                String email = registerEmail.getText().toString().trim();
                String username = registerUsername.getText().toString().trim();
                String password = registerPassword.getText().toString().trim();
                String Nic = registerNIC.getText().toString().trim();
                String phoneNumber = registerPhoneNumber.getText().toString().trim();

                User user = new User(email,username,password,Nic,phoneNumber);
                regReference.child(username).setValue(user);

                Toast.makeText(Register.this, "Account Created!", Toast.LENGTH_SHORT).show();

                Intent registerIntent = new Intent(Register.this,Login.class);
                startActivity(registerIntent);


            }
        });


    }
}