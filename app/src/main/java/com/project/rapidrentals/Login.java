package com.project.rapidrentals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText loginUserName,loginPassword;
    Button login;
    TextView registerBtn,forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUserName = findViewById(R.id.lgnUserName);
        loginPassword = findViewById(R.id.lgnPassword);
        login = findViewById(R.id.loginbtn);

        registerBtn = findViewById(R.id.lgnregister);
        forgetPassword = findViewById(R.id.ForgetPassword);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this,Register.class);
                startActivity(registerIntent);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                letLogin();
            }
        });




    }

    private void letLogin() {

        String userName = loginUserName.getText().toString().trim();
        String Password = loginPassword.getText().toString().trim();

        Query checkUser = FirebaseDatabase.getInstance().getReference("User").orderByChild("userName").equalTo(userName);



        if(userName.equals("admin")&& Password.equals("admin")){
            // adminIntent = new Intent(Login.this,.class);
            //startActivity(adminIntent);
        }else{
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {




                    if (snapshot.exists()){
                        loginUserName.setError(null);

                        String passwordDB = snapshot.child(userName).child("password").getValue(String.class);
                        if (passwordDB.equals(Password)){

                            String UserName = snapshot.child(userName).child("userName").getValue(String.class);
                            String email = snapshot.child(userName).child("email").getValue(String.class);
                            String password = snapshot.child(userName).child("password").getValue(String.class);
                            String nic = snapshot.child(userName).child("nic").getValue(String.class);
                            String PhoneNumber = snapshot.child(userName).child("phoneNumber").getValue(String.class);

                            Intent loginIntent = new Intent(Login.this,profile.class);
                            loginIntent.putExtra("userName",UserName);
                            loginIntent.putExtra("email",email);
                            loginIntent.putExtra("password",password);
                            loginIntent.putExtra("NIC",nic);
                            loginIntent.putExtra("PhoneNumber",PhoneNumber);
                            startActivity(loginIntent);
                            finish();

                        }else{
                            Toast.makeText(Login.this, "Password not correct!", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(Login.this, "No user Exist!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Login.this, "Database Error!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}