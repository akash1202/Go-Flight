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

public class AddRouteActivity extends AppCompatActivity {
    EditText etSource,etDestination,etAirport,etPrice;
    Spinner spinAirways,spinFromAmpm,spinToAmpm,spinTotalDays,spinRoute,spinStops,spinTotalHours;
    TextView tvFromTime,tvToTime;
    Button btnAddHotel;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_route);

        getSupportActionBar().setTitle("Add Route");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etSource=(EditText)findViewById(R.id.etSource);
        etDestination=(EditText)findViewById(R.id.etDestination);
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


        btnAddHotel=(Button)findViewById(R.id.btnAddHotel);
        btnAddHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRoute();
            }
        });

    }

    public  void addRoute() {

        String fromtime=tvFromTime.getText().toString()+spinFromAmpm.getSelectedItem().toString();
        String totime=tvToTime.getText().toString()+spinToAmpm.getSelectedItem().toString();


        pd= new ProgressDialog(AddRouteActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.addroutes(etSource.getText().toString(),etDestination.getText().toString(),etAirport.getText().toString(),
                spinAirways.getSelectedItem().toString(),fromtime,totime,spinTotalDays.getSelectedItem().toString(),spinRoute.getSelectedItem().toString(),spinStops.getSelectedItem().toString(),spinTotalHours.getSelectedItem().toString(),
                etPrice.getText().toString());

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    startActivity(new Intent(AddRouteActivity.this, RouteInfoActivity.class));
                    finish();
                } else {
                    Toast.makeText(AddRouteActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(AddRouteActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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

    public void setmFromTimePicker() {

        // TODO Auto-generated method stub
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AddRouteActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tvFromTime.setText(selectedHour + ":" + selectedMinute);
                if(tvFromTime.getText().toString().contains("P") ||tvFromTime.getText().toString().contains("p")){
                    spinFromAmpm.setSelection(1);
                }else if(tvFromTime.getText().toString().contains("A")|| tvFromTime.getText().toString().contains("a")){
                    spinFromAmpm.setSelection(0);
                }
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
        mTimePicker = new TimePickerDialog(AddRouteActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tvToTime.setText(selectedHour + ":" + selectedMinute);
                if(tvToTime.getText().toString().contains("P") ||tvToTime.getText().toString().contains("p")){
                    spinToAmpm.setSelection(1);
                }else if(tvToTime.getText().toString().contains("A")|| tvToTime.getText().toString().contains("a")){
                    spinToAmpm.setSelection(0);
                }
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }


}