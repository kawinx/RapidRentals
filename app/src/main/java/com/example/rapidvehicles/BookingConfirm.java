package com.example.rapidvehicles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BookingConfirm extends AppCompatActivity {

    TextView startdate,enddate;
    ImageView imageview;
    EditText cname,cdate,passenger,pickuplocation,total;
    AppCompatButton fincar_btn;
    Float dayprice;
    float TOTAL;
    int num;
    String stloc,enloc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);

        SetupUI();
        CalculateTotal();
        CheckPayment();

    }

    private void SetupUI(){

        imageview = findViewById(R.id.imageview);
        startdate = findViewById(R.id.startdate);
        enddate = findViewById(R.id.enddate);
        cname = findViewById(R.id.cname);
        cdate = findViewById(R.id.cdate);
        passenger = findViewById(R.id.passenger);
        pickuplocation = findViewById(R.id.pickuplocation);
        total = findViewById(R.id.total);
        fincar_btn = findViewById(R.id.fincar_btn);

        Intent i = getIntent();
        cname.setText(i.getStringExtra("vame"));
        imageview.setImageResource(i.getIntExtra("vimage",0));
        dayprice = i.getFloatExtra("price",0);
        passenger.setText(i.getStringExtra("psg"));
        pickuplocation.setText(i.getStringExtra("cloc"));
        startdate.setText(i.getStringExtra("stdate"));
        enddate.setText(i.getStringExtra("endate"));
        stloc = i.getStringExtra("stloc");
        enloc = i.getStringExtra("enloc");

    }

    private void CheckPayment(){

        fincar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(BookingConfirm.this,Payment.class);
                i.putExtra("enloc",enloc);
                i.putExtra("vtype",cname.getText().toString());
                i.putExtra("stloc",stloc);
                i.putExtra("total",total.getText().toString());
                i.putExtra("numday",cdate.getText().toString());
                startActivity(i);
            }
        });
    }

    private void CalculateTotal(){

        cdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String q = editable.toString();
                try {
                    num = Integer.parseInt(q);
                }catch (Exception e){

                }
                TOTAL = num * dayprice;
                Log.e("CheckQTYData",""+num);
                Log.e("CheckTotal",""+TOTAL);
                total.setText("LKR"+TOTAL);


            }
        });

    }
}