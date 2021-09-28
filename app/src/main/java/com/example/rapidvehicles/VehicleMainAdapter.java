package com.example.rapidvehicles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class VehicleMainAdapter extends FirebaseRecyclerAdapter<VehicleModel, VehicleMainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public VehicleMainAdapter(@NonNull FirebaseRecyclerOptions<VehicleModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VehicleMainAdapter.myViewHolder holder, final int position, @NonNull VehicleModel model) {
        holder.modal.setText(model.getModal());
        holder.plate.setText(model.getPlate());
        holder.passenger.setText(model.getPassenger());
        holder.type.setText(model.getType());
        holder.price.setText(model.getPrice());

        Glide.with(holder.img.getContext())
                .load(model.getVurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.vehicle_update_popup))
                        .setExpanded(true,1500)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText modal = view.findViewById(R.id.txtmodal);
                EditText plate = view.findViewById(R.id.txtPlate);
                EditText passenger = view.findViewById(R.id.txtpassenger);
                EditText type = view.findViewById(R.id.txtType);
                EditText price = view.findViewById(R.id.txtPrice);
                EditText vurl = view.findViewById(R.id.txtimageUrl);

                Button  btnUpdate = view.findViewById(R.id.btnUpdate);

                modal.setText(model.getModal());
                plate.setText(model.getPlate());
                passenger.setText(model.getPassenger());
                type.setText(model.getType());
                price.setText(model.getPrice());
                vurl.setText(model.getVurl());


                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object>  map = new HashMap<>();
                        map.put("modal",modal.getText().toString());
                        map.put("plate",plate.getText().toString());
                        map.put("passenger",passenger.getText().toString());
                        map.put("type",type.getText().toString());
                        map.put("price",price.getText().toString());
                        map.put("vurl",vurl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("vehicles")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.modal.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.modal.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.modal.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("Deleted data cannot be undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("vehicles")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.modal.getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView modal,plate,passenger,type,price;

        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1);
            modal = (TextView)itemView.findViewById(R.id.modaltext);
            plate = (TextView)itemView.findViewById(R.id.platetext);
            passenger = (TextView)itemView.findViewById(R.id.passengertext);
            type = (TextView)itemView.findViewById(R.id.typetext);
            price = (TextView)itemView.findViewById(R.id.pricetext);

            btnEdit = (Button)itemView.findViewById(R.id.btEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);
        }
    }
}
