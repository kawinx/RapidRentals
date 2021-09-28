package com.example.rapidvehicles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class VehicleListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    VehicleMainAdapter vehicleMainAdapter;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<VehicleModel> options =
                new FirebaseRecyclerOptions.Builder<VehicleModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("vehicles"), VehicleModel.class)
                        .build();

        vehicleMainAdapter = new VehicleMainAdapter(options);
        recyclerView.setAdapter(vehicleMainAdapter);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddVehicle.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        vehicleMainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        vehicleMainAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<VehicleModel> options =
                new FirebaseRecyclerOptions.Builder<VehicleModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("vehicles").orderByChild("type").startAt(str).endAt(str+"~"), VehicleModel.class)
                        .build();

        vehicleMainAdapter = new VehicleMainAdapter(options);
        vehicleMainAdapter.startListening();
        recyclerView.setAdapter(vehicleMainAdapter);
    }
}
