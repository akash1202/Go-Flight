package com.example.goflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class admin_route_list extends AppCompatActivity {
    RecyclerView recyclerView;
    Route_Retrieve_Adapter route_retrieve_adapter;
    FirebaseDatabase db;
    DatabaseReference ref;
    List<RouteListRetrieve> list;
    Button addroute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_route_list);
        recyclerView = findViewById(R.id.recylerviewID);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addroute = findViewById(R.id.AddRoute_btn);
        addroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_route_list.this,admin_AddRoute.class);
                startActivity(intent);
            }
        });

       /* FirebaseRecyclerOptions<RouteListRetrieve> options  =
                new FirebaseRecyclerOptions.Builder<RouteListRetrieve>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("routes"),RouteListRetrieve.class)
                        .build();
*/
db = FirebaseDatabase.getInstance();
ref = db.getReference("routes");
list = new ArrayList<>();
ref.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
            RouteListRetrieve routeListRetrieve = snapshot.getValue(RouteListRetrieve.class);
            routeListRetrieve.getRouteid();
            routeListRetrieve.getDeparturecity();
            routeListRetrieve.getArrivalcity();
            list.add(routeListRetrieve);
        }

        route_retrieve_adapter = new Route_Retrieve_Adapter(admin_route_list.this,list);
        recyclerView.setAdapter(route_retrieve_adapter);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
        //route_retrieve_adapter = new Route_Retrieve_Adapter(options);
        //recyclerView.setAdapter(route_retrieve_adapter);
    }





}