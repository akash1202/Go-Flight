package com.flightbooking.activies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;
import com.flightbooking.model.RouteInfoPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RouteInfoActivity extends AppCompatActivity {
    List<RouteInfoPojo> routeInfoPojo;
    ListView list_view;
    ProgressDialog progressDialog;
    Button btnRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_info);

        getSupportActionBar().setTitle("Route Information");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnRoute=(Button)findViewById(R.id.btnRoute);
        btnRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RouteInfoActivity.this, AddRouteActivity.class);
                startActivity(intent);
            }
        });

        list_view=(ListView)findViewById(R.id.list_view);

        routeInfoPojo=new ArrayList<>();
        getRouteInfo();


    }
    public void getRouteInfo(){
        progressDialog = new ProgressDialog(RouteInfoActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<RouteInfoPojo>> call = service.getroutes();
        call.enqueue(new Callback<List<RouteInfoPojo>>() {
            @Override
            public void onResponse(Call<List<RouteInfoPojo>> call, Response<List<RouteInfoPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(RouteInfoActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    routeInfoPojo = response.body();
                    list_view.setAdapter(new RouteInfoAdapter(routeInfoPojo, RouteInfoActivity.this));

                }
            }
            @Override
            public void onFailure(Call<List<RouteInfoPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RouteInfoActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}