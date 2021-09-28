package com.example.rapidvehicles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.rapidvehicles.Models.Payments;

import java.util.Calendar;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Payment extends AppCompatActivity {

    ImageView visa,american,master;
    DatePickerDialog.OnDateSetListener setListener;
    EditText cname,nonname,CVV;
    RadioButton terms;
    TextView expiredate;
    AppCompatButton btn_okay,btn_cancel;
    String cardnum;
    DatabaseReference dbRef;
    int year;
    int day;
    int month;
    String stdate,enloc,stloc,cloc,lstaus,dstaus,total,cdate,vname,dnow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        visa = findViewById(R.id.visa);
        american = findViewById(R.id.american);
        master = findViewById(R.id.master);
        cname = findViewById(R.id.cname);
        nonname = findViewById(R.id.nonname);
        expiredate = findViewById(R.id.expiredate);
        CVV = findViewById(R.id.CVV);
        terms = findViewById(R.id.terms);
        btn_okay = findViewById(R.id.btn_okay);
        btn_cancel = findViewById(R.id.btn_cancel);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Payments");

        cardnumber();
        SelectExpireDate();
        GetDataIntent();
        DateSet();

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;
                stdate = i+"/"+i1+"/"+i2;
                expiredate.setText(stdate);


            }
        };

        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddDatatoDB();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i  = new Intent(Payment.this,HomeActivity.class);
                startActivity(i);
            }
        });

    }

    private void cardnumber(){
        cname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                cardnum = cname.getText().toString();
                if(cardnum.startsWith("3")){
                    master.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
                    visa.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
                    american.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#AE03A9F4")));

                }
                else if(cardnum.startsWith("4")){
                    american.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
                    master.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
                    visa.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#AE03A9F4")));
                }
                else if(cardnum.startsWith("5")){
                    american.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
                    visa.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
                    master.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#AE03A9F4")));;
                }
                else if(cardnum.startsWith("")){
                    american.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
                    master.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
                    visa.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
                }
            }
        });
    }

    private void GetDataIntent(){
        MainActivity mainActivity = new MainActivity();
        Intent i = getIntent();
        enloc = i.getStringExtra("enloc");
        cloc = i.getStringExtra("cloc");
        vname = i.getStringExtra("vtype");
        lstaus = mainActivity.locationstatus;
        dstaus = mainActivity.drivestatus;
        cdate = i.getStringExtra("numday");
        stloc = i.getStringExtra("stloc");
        total = i.getStringExtra("total");



    }


    private void SelectExpireDate(){
        expiredate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(Payment.this, android.R.style.Theme,setListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });


    }

    private void DateSet(){
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        dnow = String.valueOf(now);
        Log.e("CheckData",dnow);
    }


    private void AddDatatoDB(){



        String cardNum = cname.getText().toString();
        String cardName = nonname.getText().toString();
        String expDate = expiredate.getText().toString();
        String NCvv = CVV.getText().toString();

        if(cardNum.isEmpty() || cardName.isEmpty() || expDate.isEmpty() || NCvv.isEmpty()){
            Toast.makeText(Payment.this, "Please Enter all fields", Toast.LENGTH_SHORT).show();
        }
        else if(!terms.isChecked()){
            Toast.makeText(Payment.this, "Please Agree to the terms", Toast.LENGTH_SHORT).show();
        }
        else {
            Payments payments = new Payments(cardNum,cardName,NCvv,expDate,total,cloc,dnow,vname,cdate,lstaus,dstaus);
            dbRef.push().setValue(payments).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    CompleteAlert();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    faileAlert();
                }
            });
        }
    }

    private void CompleteAlert(){

        SweetAlertDialog pdialog = new SweetAlertDialog(Payment.this, SweetAlertDialog.SUCCESS_TYPE);
        pdialog.setTitleText("Complete");
        pdialog.setContentText("Item Payment Successfully");
        pdialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                sweetAlertDialog.dismissWithAnimation();
                Intent i = new Intent(Payment.this,ViewPayments.class);
                startActivity(i);
            }
        });
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.show();
    }

    private void faileAlert(){
        SweetAlertDialog pdialog = new SweetAlertDialog(Payment.this, SweetAlertDialog.ERROR_TYPE);
        pdialog.setTitleText("Incomplete");
        pdialog.setContentText("Something went Wrong!");
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
