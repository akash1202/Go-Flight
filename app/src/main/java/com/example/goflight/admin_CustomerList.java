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

public class admin_CustomerList extends AppCompatActivity {
    RecyclerView recyclerView;
    Customer_Retrieve_Adapter customer_retrieve_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__customer_list);
        recyclerView = findViewById(R.id.recylerviewID);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CustomerListRetrieve>options  =
                new FirebaseRecyclerOptions.Builder<CustomerListRetrieve>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("users"),CustomerListRetrieve.class)
                .build();

        customer_retrieve_adapter = new Customer_Retrieve_Adapter(options);
        recyclerView.setAdapter(customer_retrieve_adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<CustomerListRetrieve> options =
                new FirebaseRecyclerOptions.Builder<CustomerListRetrieve>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users").orderByChild("firstname").startAt(s).endAt(s+"\uf8ff"), CustomerListRetrieve.class)
                        .build();

        customer_retrieve_adapter=new Customer_Retrieve_Adapter(options);
        customer_retrieve_adapter.startListening();
        recyclerView.setAdapter(customer_retrieve_adapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        customer_retrieve_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        customer_retrieve_adapter.stopListening();
    }

}