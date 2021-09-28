package com.example.rapidvehicles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textview_select,start,end;
    FusedLocationProviderClient fusedLocationProviderClient;
    int PLACE_PICKER_REQUEST = 1;
    RadioButton radio1,radio2,radio3,radio4;
    AppCompatButton btn;
    String stloc,enloc,cloc;
    public static String drivestatus;
    public static String locationstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
        SetUpUI();
        SetCurrentLocation();


    }

    private void SetUpUI() {

        textview_select = findViewById(R.id.textview_select);
        start = findViewById(R.id.start);
        end = findViewById(R.id.end);
        btn = findViewById(R.id.fincar_btn);
        radio1 =findViewById(R.id.radio1);
        radio2 =findViewById(R.id.radio2);
        radio3 =findViewById(R.id.radio3);
        radio4 =findViewById(R.id.radio4);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driveStatus();
                locationstatus();
                stloc = start.getText().toString();
                enloc = end.getText().toString();
                cloc = textview_select.getText().toString();
                if (stloc.isEmpty() || enloc.isEmpty() || cloc.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(MainActivity.this, SelectCar.class);
                    i.putExtra("stloc",stloc);
                    i.putExtra("enloc",enloc);
                    i.putExtra("cloc",cloc);
                    startActivity(i);
                }
            }
        });
    }

    private void SetCurrentLocation() {

        textview_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    //permission denied
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }


            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                //location initizialize
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                        List<Address> addressList = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        //set lattitued on text
                        textview_select.setText(Html.fromHtml("<font color ='#6200EE'><b>Location:</b><br></font>"
                                + addressList.get(0).getAddressLine(0)));

                        //set longtitute
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void driveStatus(){

        if (radio1.isChecked()){
            drivestatus = "Need Driver";
            radio2.setChecked(false);
        }
        else if(radio2.isChecked()){
            drivestatus = "Self Drive";
            radio1.setChecked(false);
        }

    }

    private void locationstatus(){

        if (radio3.isChecked()){
            locationstatus ="Home Drop";
            radio4.setChecked(false);
        }
        else if(radio4.isChecked()){
            locationstatus ="Pick Up";
            radio3.setChecked(false);
        }
    }





}