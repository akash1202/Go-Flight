package com.flightbooking.activies;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;
import com.flightbooking.adapters.CustomerProfileAdapter;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCustomerProfileActivity extends AppCompatActivity {
    List<MyProfilePojo> custprofile;
    ListView list_view;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_customer_profile);

        getSupportActionBar().setTitle("Customer Profiles");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);

        custprofile=new ArrayList<>();
        getcustprofile();

    }
    public void getcustprofile(){
        progressDialog = new ProgressDialog(GetCustomerProfileActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<MyProfilePojo>> call = service.getcustprofile();
        call.enqueue(new Callback<List<MyProfilePojo>>() {
            @Override
            public void onResponse(Call<List<MyProfilePojo>> call, Response<List<MyProfilePojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(GetCustomerProfileActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    custprofile = response.body();
                    list_view.setAdapter(new CustomerProfileAdapter(custprofile, GetCustomerProfileActivity.this));

                }
            }
            @Override
            public void onFailure(Call<List<MyProfilePojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(GetCustomerProfileActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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