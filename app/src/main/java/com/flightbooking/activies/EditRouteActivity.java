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
    String fromAmPm="",toAmPm="";

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

        tvFromTime.setText(getIntent().getStringExtra("frmtim").toLowerCase().replace("am","").replace("pm",""));
        tvToTime.setText(getIntent().getStringExtra("totim").toLowerCase().replace("am","").replace("pm",""));
        etAirport.setText(getIntent().getStringExtra("airport"));
        etPrice.setText(getIntent().getStringExtra("price"));

        String stops[]=getResources().getStringArray(R.array.stops);
        String tdays[]=getResources().getStringArray(R.array.tdays);
        String routeType[]=getResources().getStringArray(R.array.route);
        String totalHours[]=getResources().getStringArray(R.array.totalhours);
        String airways[]=getResources().getStringArray(R.array.airways);

        for(int i=1;i<stops.length;i++){
            if(Integer.parseInt(stops[i])==Integer.parseInt(getIntent().getStringExtra("stops"))){
                spinStops.setSelection(i);
            }
        }
        for(int i=1;i<routeType.length;i++){
           // if(routeType[i].equals(getIntent().getStringExtra("route"))){
                    spinRoute.setSelection(1); //one way is default
           // }
        }
        for(int i=1;i<tdays.length;i++){
            if(containsSameDays(tdays[i],getIntent().getStringExtra("tdays"))){
                spinTotalDays.setSelection(i);
            }
        }
        for(int i=1;i<totalHours.length;i++){
            if(totalHours[i].contains(""+Integer.parseInt(getIntent().getStringExtra("layour").replace(" ","").toLowerCase().replace("hours","")))){
                spinTotalHours.setSelection(i);
            }
        }
        for(int i=1;i<airways.length;i++){
            if(airways[i].toLowerCase().replace(" ","").contains(""+getIntent().getStringExtra("airways").toLowerCase().replace(" ",""))){
                spinAirways.setSelection(i);
            }
        }
        if(getIntent().getStringExtra("frmtim").toLowerCase().contains("p")){
            spinFromAmpm.setSelection(1);
        }else if(getIntent().getStringExtra("frmtim").toLowerCase().contains("a")){
            spinFromAmpm.setSelection(0);
        }
        if(getIntent().getStringExtra("totim").toLowerCase().contains("p")){
            spinToAmpm.setSelection(1);
        }else if(getIntent().getStringExtra("totim").toLowerCase().contains("a")){
            spinToAmpm.setSelection(0);
        }

        btnEditRoute=(Button)findViewById(R.id.btnAddHotel);
        btnEditRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editRoute();
            }
        });
    }


    public boolean containsSameDays(String first,String second){
        String[] dayOne=first.replace(" ","").split("-");
        String[] daySecond=second.replace(" ","").split("-");
        if(dayOne[0].equalsIgnoreCase(daySecond[0])&&dayOne[1].equalsIgnoreCase(daySecond[1]))
            return true;
        else
            return false;
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
                fromtime+fromAmPm,
                totime+toAmPm,
                spinTotalDays.getSelectedItem().toString(),
                spinRoute.getSelectedItem().toString(),
                spinStops.getSelectedItem().toString(),
                spinTotalHours.getSelectedItem().toString(),
                rid,
                etPrice.getText().toString());

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

                if(selectedHour>12){
                    selectedHour=selectedHour-12;
                    fromAmPm="PM";
                    spinFromAmpm.setSelection(1);
                }else{
                    fromAmPm="AM";
                    spinFromAmpm.setSelection(0);
                }
                tvFromTime.setText(selectedHour+ ":" + selectedMinute+"");
            }
        }, hour, minute, false);//Yes 24 hour time
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
                if(selectedHour>12){
                    selectedHour=selectedHour-12;
                    toAmPm="PM";
                    spinToAmpm.setSelection(1);
                }else{
                    toAmPm="AM";
                    spinToAmpm.setSelection(0);
                }
                tvToTime.setText(selectedHour+ ":" + selectedMinute+"");
            }
        }, hour, minute, false);//Yes 24 hour time
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