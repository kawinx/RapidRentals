package com.project.rapidrentals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.rapidrentals.Models.User;

public class EditProfile extends AppCompatActivity {

    TextView username,email,password,nic,phoneNumber;
    Button save;

    DatabaseReference profileeditref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username = findViewById(R.id.editprflUserName);
        email = findViewById(R.id.editprflEmail);
        password = findViewById(R.id.editprflPassword);
        nic = findViewById(R.id.editprflNic);
        phoneNumber = findViewById(R.id.editprflPhoneNumber);
        save = findViewById(R.id.editprflsave);


        Intent editprflIntent = getIntent();

        String UserName = editprflIntent.getStringExtra("userName");
        String Email = editprflIntent.getStringExtra("email");
        String Password = editprflIntent.getStringExtra("password");
        String NIC = editprflIntent.getStringExtra("NIC");
        String PhoneNumber = editprflIntent.getStringExtra("PhoneNumber");


        username.setText(UserName);
        email.setText(Email);
        password.setText(Password);
        nic.setText(NIC);
        phoneNumber.setText(PhoneNumber);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editUserName = username.getText().toString().trim();
                String editEmail = email.getText().toString().trim();
                String editPassword = password.getText().toString().trim();
                String editNIC = nic.getText().toString().trim();
                String editPhoneNumber = phoneNumber.getText().toString().trim();


                profileeditref = FirebaseDatabase.getInstance().getReference("User").child(UserName);
                User user = new User(editEmail,editUserName,editPassword,editNIC,editPhoneNumber);
                profileeditref.setValue(user);

                Toast.makeText(EditProfile.this, "Profile Updated!", Toast.LENGTH_SHORT).show();

                Intent profileIntent = new Intent(EditProfile.this,profile.class);
                startActivity(profileIntent);
                finish();

            }
        });

    }
}