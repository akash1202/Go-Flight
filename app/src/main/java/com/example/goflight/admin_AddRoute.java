package com.example.goflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class admin_AddRoute extends AppCompatActivity {
    private FirebaseDatabase db;
    private EditText etrouteid,etdeparture_province,etdeparture_city,etdeparture_airport,etarrival_city,etarrival_province,etarrival_airport;
    private Button btnaddroute;

       @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__add_route);
           db= FirebaseDatabase.getInstance();
        etrouteid=findViewById(R.id.edt_RouteID);
        etdeparture_province=findViewById(R.id.edt_DepartureProvince);
        etdeparture_city=findViewById(R.id.edt_DepartureCity);
        etdeparture_airport=findViewById(R.id.edt_DepartureAirport);
        etarrival_province=findViewById(R.id.edt_ArrivalProvince);
        etarrival_city=findViewById(R.id.edt_ArrivalCity);
        etarrival_airport=findViewById(R.id.edt_ArrivalAirport);
        btnaddroute=findViewById(R.id.route_btn);

        btnaddroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRoute();
            }
        });


      }
    private void addRoute(){
        boolean error=false;
        final String routeid=etrouteid.getText().toString();
        final String departureprovince=etdeparture_province.getText().toString();
        final String departurecity=etdeparture_city.getText().toString();
        final String departureairport=etdeparture_airport.getText().toString();
        final String arrivalprovince=etarrival_province.getText().toString();
        final String arrivalcity=etarrival_city.getText().toString();
        final String arrivalairport=etarrival_airport.getText().toString();


        if(routeid.length()==0){
            etrouteid.setError("RouteID required...");
            etrouteid.requestFocus();
            error=true;
        }else{
            etrouteid.setError(null);

        }
        if(departureprovince.length()==0){
            etdeparture_province.setError("Departure Province required...");
            etdeparture_province.requestFocus();
            error=true;
        }else{
            etdeparture_province.setError(null);

        }
        if(departurecity.length()==0){
            etdeparture_city.setError("Departure City required...");
            etdeparture_city.requestFocus();
            error=true;
        }else{
            etdeparture_city.setError(null);

        }
        if(departureairport.length()==0){
            etdeparture_airport.setError("Departure Airport required...");
            etdeparture_airport.requestFocus();
            error=true;
        }else{
            etdeparture_airport.setError(null);

        }
        if(arrivalprovince.length()==0){
            etarrival_province.setError("Arrival Province required...");
            etarrival_province.requestFocus();
            error=true;
        }else{
            etarrival_province.setError(null);

        }
        if(arrivalcity.length()==0){
            etarrival_city.setError("Arrival City required...");
            etdeparture_city.requestFocus();
            error=true;
        }else{
            etarrival_city.setError(null);

        }
        if(arrivalairport.length()==0){
            etarrival_airport.setError("Arrival Airport required...");
            etarrival_airport.requestFocus();
            error=true;
        }else{
            etarrival_airport.setError(null);

        }
        if(!error){
                        // Create a new user with a first and last name
                        Map<String, Object> route = new HashMap<>();
            route.put("routeid",routeid);
            route.put("departureprovince",departureprovince);
            route.put("departurecity",departurecity);
            route.put("departureairport",departureairport);
            route.put("arrivalprovince",arrivalprovince);
            route.put("arrivalcity",arrivalcity);
            route.put("arrivalairport",arrivalairport);
            // Add a new document with a generated ID

                        db.getReference("routes").push()
                                .setValue(route)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){
                                            Intent objIntent=new Intent(admin_AddRoute.this,admin_route_list.class);
                                            startActivity(objIntent);
                                            finish();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(admin_AddRoute.this,"Error Creating Add :: "+e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }else{
                        Toast.makeText(admin_AddRoute.this,"Unable to add the Route...",Toast.LENGTH_LONG).show();
                    }



                }

    }
