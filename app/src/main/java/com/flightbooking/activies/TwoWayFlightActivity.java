package com.flightbooking.activies;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;

import java.util.Calendar;

public class TwoWayFlightActivity extends AppCompatActivity {
    Spinner spinSource,spinDest,spinAdult,spinChildern,spinClassType,spinPayin,spinType;
    Button btnSearchFlights;
    TextView returnTextView;
    EditText etDeparture,etReturn;
    int mYear, mMonth, mDay;
    String DAY, MONTH, YEAR;
    long departureDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_way_flight);

        getSupportActionBar().setTitle("Two Way");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinSource=(Spinner)findViewById(R.id.spinSource);
        spinDest=(Spinner)findViewById(R.id.spinDest);
        spinAdult=(Spinner)findViewById(R.id.spinAdult);
        spinChildern=(Spinner)findViewById(R.id.spinChildern);
        spinClassType=(Spinner)findViewById(R.id.spinClassType);
        spinPayin=(Spinner)findViewById(R.id.spinPayin);
        etDeparture=(EditText)findViewById(R.id.etDeparture);
        etDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                departuredate();
            }
        });
        etReturn=(EditText)findViewById(R.id.etReturn);
        etDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                departuredate();
            }
        });
        etReturn=(EditText)findViewById(R.id.etReturn);
        returnTextView=findViewById(R.id.returnTextView);
        etReturn.setClickable(false);
        etReturn.setEnabled(false);
        etReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnDate();
            }
        });
        etReturn.setFocusable(false);
        etDeparture.setFocusable(false);
        showOneWay();
        returnTextView=(TextView) findViewById(R.id.returnTextView);
        Button btnSearchFlights=(Button)findViewById(R.id.btnSearchFlights);
        btnSearchFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spinSource.getSelectedItem().toString().isEmpty()){
                    Toast.makeText(TwoWayFlightActivity.this, "Please choose source", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(spinDest.getSelectedItem().toString().isEmpty()){
                    Toast.makeText(TwoWayFlightActivity.this, "Please choose destination", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etDeparture.getText().toString().isEmpty()){
                    Toast.makeText(TwoWayFlightActivity.this, "Please choose departure date", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etReturn.getText().toString().isEmpty()){
                    Toast.makeText(TwoWayFlightActivity.this, "Please choose return date", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(spinAdult.getSelectedItem().toString().equals("Adults")){
                    Toast.makeText(TwoWayFlightActivity.this, "Please choose Passenger adults", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(spinChildern.getSelectedItem().toString().equals("Childrens")){
                    Toast.makeText(TwoWayFlightActivity.this, "Please choose passengers childrens", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(spinChildern.getSelectedItem().equals("0")&&spinAdult.getSelectedItem().equals("0")){
                    Toast.makeText(TwoWayFlightActivity.this, "Please choose atleast one passenger", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(spinClassType.getSelectedItem().toString().equals("Economy")){
                    Toast.makeText(TwoWayFlightActivity.this, "Please choose Classtype", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(spinChildern.getSelectedItem().toString().isEmpty()){
                    Toast.makeText(TwoWayFlightActivity.this, "Please choose currency type", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(TwoWayFlightActivity.this, AvailableFlightsActivity.class);
                intent.putExtra("source",spinSource.getSelectedItem().toString());
                intent.putExtra("destination",spinDest.getSelectedItem().toString());
                intent.putExtra("type","Two Way");
                intent.putExtra("date",etDeparture.getText().toString());
                intent.putExtra("adult",spinAdult.getSelectedItem().toString());
                intent.putExtra("children",spinChildern.getSelectedItem().toString());
                intent.putExtra("classtype",spinClassType.getSelectedItem().toString());
                intent.putExtra("currency",spinPayin.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }

    public void showOneWay(){
        returnTextView.setVisibility(View.GONE);
        etReturn.setVisibility(View.GONE);
        etReturn.setEnabled(false);
    }

    public void showTwoWays(){
        returnTextView.setVisibility(View.VISIBLE);
        etReturn.setVisibility(View.VISIBLE);
    }

    public void departuredate() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        DAY = dayOfMonth + "";
                        MONTH = monthOfYear + 1 + "";
                        YEAR = year + "";
                        etDeparture.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        showTwoWays();
                        etReturn.setEnabled(true);
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.DAY_OF_MONTH, view.getDayOfMonth());
                        cal.set(Calendar.MONTH, view.getMonth());
                        cal.set(Calendar.YEAR, view.getYear());
                        departureDate=cal.getTime().getTime();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }


    public void returnDate() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        DAY = dayOfMonth + "";
                        MONTH = monthOfYear + 1 + "";
                        YEAR = year + "";
                        etReturn.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(departureDate+24*60*60*1000);
        datePickerDialog.show();
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