package com.flightbooking.activies;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;
import com.flightbooking.model.ResponseData;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditRouteActivity extends AppCompatActivity {

    EditText etAirport,etPrice;
    Spinner spinAirways,spinFromAmpm,spinToAmpm,spinTotalDays,spinRoute,spinStops,spinTotalHours,etSource,etDestination;
    TextView tvFromTime,tvToTime;
    Button btnEditRoute;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_route);

        getSupportActionBar().setTitle("Edit Route");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etSource=(Spinner) findViewById(R.id.etSource);
        etDestination=(Spinner)findViewById(R.id.etDestination);
        etAirport=(EditText)findViewById(R.id.etAirport);
        etPrice=(EditText)findViewById(R.id.etPrice);

        spinAirways=(Spinner)findViewById(R.id.spinAirways);
        spinFromAmpm=(Spinner)findViewById(R.id.spinFromAmpm);
        spinToAmpm=(Spinner)findViewById(R.id.spinToAmpm);
        spinTotalDays=(Spinner)findViewById(R.id.spinTotalDays);
        spinRoute=(Spinner)findViewById(R.id.spinRoute);

        spinStops=(Spinner)findViewById(R.id.spinStops);
        spinTotalHours=(Spinner)findViewById(R.id.spinTotalHours);

        tvFromTime=(TextView)findViewById(R.id.tvFromTime);
        tvFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setmFromTimePicker();

            }
        });
        tvToTime=(TextView)findViewById(R.id.tvToTime);
        tvToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setmToTimePicker();
            }
        });
       // carname=getIntent().getStringExtra("carname");

//        intent.putExtra("source",routeInfoPojo.get(pos).getSource());
//        intent.putExtra("destination",routeInfoPojo.get(pos).getDestination());
//        intent.putExtra("airport",routeInfoPojo.get(pos).getAirport());
//        intent.putExtra("airways",routeInfoPojo.get(pos).getAirways());
//        intent.putExtra("frmtim",routeInfoPojo.get(pos).getFrmtim());
//        intent.putExtra("totim",routeInfoPojo.get(pos).getTotim());
//        intent.putExtra("tdays",routeInfoPojo.get(pos).getTdays());
//        intent.putExtra("type",routeInfoPojo.get(pos).getType());
//        intent.putExtra("stops",routeInfoPojo.get(pos).getStops());
//        intent.putExtra("layour",routeInfoPojo.get(pos).getLayour());
//        intent.putExtra("rid",routeInfoPojo.get(pos).getRid());
//        intent.putExtra("price",routeInfoPojo.get(pos).getPrice());

        tvFromTime.setText(getIntent().getStringExtra("frmtim"));
        tvToTime.setText(getIntent().getStringExtra("totim"));
        if(tvFromTime.getText().toString().contains("P") ||tvFromTime.getText().toString().contains("p")){
            spinFromAmpm.setSelection(1);
        }else if(tvFromTime.getText().toString().contains("A")|| tvFromTime.getText().toString().contains("a")){
            spinFromAmpm.setSelection(0);
        }
        if(tvToTime.getText().toString().contains("P") ||tvToTime.getText().toString().contains("p")){
            spinToAmpm.setSelection(1);
        }else if(tvToTime.getText().toString().contains("A")|| tvToTime.getText().toString().contains("a")){
            spinToAmpm.setSelection(0);
        }
        etAirport.setText(getIntent().getStringExtra("airport"));
        etPrice.setText(getIntent().getStringExtra("price"));

        btnEditRoute=(Button)findViewById(R.id.btnAddHotel);
        btnEditRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editRoute();
            }
        });
    }

    public  void editRoute() {

        String fromtime=tvFromTime.getText().toString().trim().length() > 4 ? tvFromTime.getText().toString().trim()+spinFromAmpm.getSelectedItem().toString(): tvFromTime.getText().toString().trim();
        String totime=tvToTime.getText().toString().trim().length() > 4 ? tvToTime.getText().toString().trim()+spinToAmpm.getSelectedItem().toString(): tvToTime.getText().toString().trim();

        String rid=getIntent().getStringExtra("rid");
        pd= new ProgressDialog(EditRouteActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.editroute(etSource.getSelectedItem().toString(),
                etDestination.getSelectedItem().toString(),
                etAirport.getText().toString(),
                spinAirways.getSelectedItem().toString(),
                fromtime,
                totime,
                spinTotalDays.getSelectedItem().toString(),
                spinRoute.getSelectedItem().toString(),
                spinStops.getSelectedItem().toString(),
                spinTotalHours.getSelectedItem().toString(),
                rid,
                etPrice.getText().toString());

        //                @Query("source") String source,
//                @Query("destination") String destination,
//                @Query("airport") String airport,
//                @Query("airways") String airways,
//                @Query("frmtim") String frmtim,
//                @Query("totim") String totim,
//                @Query("tdays") String tdays,
//                @Query("type") String type,
//                @Query("stops") String stops,
//                @Query("layour") String layour,
//                @Query("rid") String rid,
//                @Query("price") String price);


        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    startActivity(new Intent(EditRouteActivity.this, RouteInfoActivity.class));
                    finish();
                } else {
                    Toast.makeText(EditRouteActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(EditRouteActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void setmFromTimePicker() {

        // TODO Auto-generated method stub
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(EditRouteActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tvFromTime.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    public void setmToTimePicker() {

        // TODO Auto-generated method stub
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(EditRouteActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tvToTime.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

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