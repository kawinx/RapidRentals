package com.project.rapidrentals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.rapidrentals.Models.feedback;

public class feedbackActivity extends AppCompatActivity {

    EditText feedbackET;
    Button submit;

    DatabaseReference feedbackref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackET = findViewById(R.id.feedback);
        submit = findViewById(R.id.submit);


        Intent feedbackIntent = getIntent();
        String UserName = feedbackIntent.getStringExtra("userName");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback1 = feedbackET.getText().toString().trim();

                feedbackref = FirebaseDatabase.getInstance().getReference("Feedback").child(UserName);

                feedback feedbacks = new feedback(UserName,feedback1);
                feedbackref.setValue(feedbacks);
            }
        });

    }
}