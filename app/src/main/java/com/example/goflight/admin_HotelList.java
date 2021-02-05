package com.example.goflight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class admin_HotelList extends AppCompatActivity {
    RecyclerView recyclerView;
    Hotel_Retrieve_Adapter hotel_retrieve_adapter ;
    Button addHotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__hotel_list);
        recyclerView = findViewById(R.id.recylerviewID);
        addHotel = findViewById(R.id.AdddHotel_btn);
        addHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_HotelList.this,admin_add_hotel.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<hotelmodel>options  =
                new FirebaseRecyclerOptions.Builder<hotelmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Images"),hotelmodel.class)
                        .build();

        hotel_retrieve_adapter = new Hotel_Retrieve_Adapter(options);
        recyclerView.setAdapter(hotel_retrieve_adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        hotel_retrieve_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        hotel_retrieve_adapter.stopListening();
    }

}