package com.example.doctest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AppointmentActivity extends AppCompatActivity {
    private RecyclerView appoint_row;
    public DatabaseReference appointdeataildatabase,confirmdatabase,rejectdatabse;
    FirebaseRecyclerAdapter appointrecycleradapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        appoint_row = (RecyclerView) findViewById(R.id.appoint_row);
        appoint_row.setHasFixedSize(true);
        appoint_row.setLayoutManager(new LinearLayoutManager(this));
        appointdeataildatabase = FirebaseDatabase.getInstance().getReference("Appointment").child("Pending Appointment").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        appointdeataildatabase.keepSynced(true);
        confirmdatabase = FirebaseDatabase.getInstance().getReference("Appointment").child("Confirm Appointment");
        rejectdatabse = FirebaseDatabase.getInstance().getReference("Appointment").child("Rejected Appointment");



    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<appointment> optioner=
                new FirebaseRecyclerOptions.Builder<appointment>()
                        .setQuery(appointdeataildatabase,appointment.class)
                        .setLifecycleOwner(this)
                        .build();

        appointrecycleradapter = new FirebaseRecyclerAdapter<appointment,appointViewHolder>(optioner){

            @NonNull
            @Override
            public appointViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new appointViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cardappoint, parent, false));
            }

            @Override
            protected void onBindViewHolder(appointViewHolder holder,final int position, final appointment model) {
                holder.setMobilenumber(model.getMobilenumber());
                holder.setAddress(model.getAddress());
                holder.setCity(model.getCity());
                holder.setFullname(model.getFullname());
                holder.setState(model.getState());
                holder.setPincode(model.getPincode());
                final String dockey = model.getDockey();
                final String appointkey = model.getAppointkey();
                holder.confirmCustAppointButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference confirmappoint = confirmdatabase.child(dockey).child(appointkey);
                        confirmappoint.child("fullname").setValue(model.getFullname());
                        confirmappoint.child("userid").setValue(model.getUserid());
                        confirmappoint.child("appointkey").setValue(appointkey);
                        confirmappoint.child("dockey").setValue(dockey);
                        confirmappoint.child("docName").setValue(model.getDocName());
                        confirmappoint.child("docProf").setValue(model.getDocProf());
                        confirmappoint.child("descriptionDoc").setValue(model.getDescriptionDoc());
                        confirmappoint.child("mobilenumber").setValue(model.getMobilenumber());
                        confirmappoint.child("address").setValue(model.getAddress());
                        confirmappoint.child("city").setValue(model.getCity());
                        confirmappoint.child("state").setValue(model.getState());
                        confirmappoint.child("age").setValue(model.getAge());
                        confirmappoint.child("pincode").setValue(model.getPincode());
                        confirmappoint.child("description").setValue(model.getDescription());
                        confirmappoint.child("gender").setValue(model.getGender());
                        confirmappoint.child("usseregrid").setValue(model.getUserregid());
                        appointdeataildatabase =FirebaseDatabase.getInstance().getReference("Appointment").child("Pending Appointment").child("-Ls_xBYtdJ2-SWji-tzd").child(model.getAppointkey());
                        appointdeataildatabase.removeValue();
                        startActivity(new Intent(getApplicationContext(), AppointmentActivity.class));




                    }
                });
                holder.rejectCustAppointButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference rejectappoint = rejectdatabse.child(dockey).child(appointkey);
                        rejectappoint.child("fullname").setValue(model.getFullname());
                        rejectappoint.child("userid").setValue(model.getUserid());
                        rejectappoint.child("appointkey").setValue(appointkey);
                        rejectappoint.child("dockey").setValue(dockey);
                        rejectappoint.child("docName").setValue(model.getDocName());
                        rejectappoint.child("docProf").setValue(model.getDocProf());
                        rejectappoint.child("descriptionDoc").setValue(model.getDescriptionDoc());
                        rejectappoint.child("mobilenumber").setValue(model.getMobilenumber());
                        rejectappoint.child("address").setValue(model.getAddress());
                        rejectappoint.child("city").setValue(model.getCity());
                        rejectappoint.child("state").setValue(model.getState());
                        rejectappoint.child("age").setValue(model.getAge());
                        rejectappoint.child("pincode").setValue(model.getPincode());
                        rejectappoint.child("description").setValue(model.getDescription());
                        rejectappoint.child("gender").setValue(model.getGender());
                        rejectappoint.child("usseregrid").setValue(model.getUserregid());
                        appointdeataildatabase =FirebaseDatabase.getInstance().getReference("Appointment").child("Pending Appointment").child("-Ls_xBYtdJ2-SWji-tzd").child(model.getAppointkey());
                        appointdeataildatabase.removeValue();
                        startActivity(new Intent(getApplicationContext(), AppointmentActivity.class));


                    }
                });


            }
        };
        appoint_row.setAdapter(appointrecycleradapter);






    }

    public static class appointViewHolder extends RecyclerView.ViewHolder{
        View view;
        Button confirmCustAppointButton;
        Button rejectCustAppointButton;

        public appointViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            confirmCustAppointButton = view.findViewById(R.id.confirmCustAppointButton);
            rejectCustAppointButton = view.findViewById(R.id.rejectCustAppointButton);


        }

        public void setMobilenumber(String mobilenumber){
            TextView mobilereg = (TextView) view.findViewById(R.id.mobilereg);
            mobilereg.setText(mobilenumber);

        }
        public void setAddress(String address){
            TextView addreg = (TextView) view.findViewById(R.id.addreg);
            addreg.setMovementMethod(new ScrollingMovementMethod());
            addreg.setText(address);
        }
        public void setPincode(String pincode){
            TextView pincodereg = (TextView) view.findViewById(R.id.pincodereg);
            pincodereg.setText(pincode);
        }
        public void setState(String state){
            TextView statereg = (TextView) view.findViewById(R.id.statereg);
            statereg.setText(state);
        }
        public void setCity(String city){
            TextView cityreg = (TextView) view.findViewById(R.id.cityreg);
            cityreg.setText(city);
        }
        public void setFullname(String fullname){
            TextView namereg = (TextView) view.findViewById(R.id.namereg);
            namereg.setText(fullname);
        }


    }
}
