package com.flightbooking.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.flightbooking.R;
import com.flightbooking.activies.AvailableFlightsActivity;
import com.flightbooking.activies.TwoWayFlightActivity;

import java.util.Calendar;


public class HomeFragment extends Fragment {
    Button btnOneway,btnTwoway;
    Spinner spinSource,spinDest,spinAdult,spinChildern,spinClassType,spinPayin;
    Button btnSearchFlights;
    View view;
    int mYear, mMonth, mDay;
    String DAY, MONTH, YEAR;
    EditText etDeparture;


    public static HomeFragment homeFragment() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");

        spinSource=(Spinner)view.findViewById(R.id.spinSource);
        spinDest=(Spinner)view.findViewById(R.id.spinDest);
        spinAdult=(Spinner)view.findViewById(R.id.spinAdult);
        spinChildern=(Spinner)view.findViewById(R.id.spinChildern);
        spinClassType=(Spinner)view.findViewById(R.id.spinClassType);
        spinPayin=(Spinner)view.findViewById(R.id.spinPayin);

        etDeparture=(EditText)view.findViewById(R.id.etDeparture);
        etDeparture.setFocusable(false);
        etDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                departuredate();
            }
        });

        btnOneway=(Button)view.findViewById(R.id.btnOneway);
        btnOneway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), OneWayFlightActivity.class));
            }
        });

        btnTwoway=(Button)view.findViewById(R.id.btnTwoway);
        btnTwoway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TwoWayFlightActivity.class));

            }
        });

        btnSearchFlights=(Button)view.findViewById(R.id.btnSearchFlights);
        btnSearchFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spinSource.getSelectedItem().toString().isEmpty()){
                    Toast.makeText(getContext(), "Please choose source", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(spinDest.getSelectedItem().toString().isEmpty()){
                    Toast.makeText(getContext(), "Please choose destination", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etDeparture.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Please choose departure date", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(spinAdult.getSelectedItem().toString().equals("Adults")){
                    Toast.makeText(getContext(), "Please choose Passenger adults", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(spinChildern.getSelectedItem().toString().equals("Childrens")){
                    Toast.makeText(getContext(), "Please choose passengers childrens", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(spinChildern.getSelectedItem().equals("0")&&spinAdult.getSelectedItem().equals("0")){
                    Toast.makeText(getContext(), "Please choose atleast one passenger", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(spinClassType.getSelectedItem().toString().equals("")){
                    Toast.makeText(getContext(), "Please choose Classtype", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(spinChildern.getSelectedItem().toString().isEmpty()){
                    Toast.makeText(getContext(), "Please choose currency type", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent=new Intent(getContext(), AvailableFlightsActivity.class);
                intent.putExtra("source",spinSource.getSelectedItem().toString());
                intent.putExtra("destination",spinDest.getSelectedItem().toString());
                intent.putExtra("type",getResources().getStringArray(R.array.route)[1]+""); //"One Way"
                intent.putExtra("date",etDeparture.getText().toString());
                intent.putExtra("adult",spinAdult.getSelectedItem().toString());
                intent.putExtra("children",spinChildern.getSelectedItem().toString());
                intent.putExtra("classtype",spinClassType.getSelectedItem().toString());
                intent.putExtra("currency",spinPayin.getSelectedItem().toString());
                startActivity(intent);
            }
        });

        return view;
    }
    public void departuredate() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        DAY = dayOfMonth + "";
                        MONTH = monthOfYear + 1 + "";
                        YEAR = year + "";

                        etDeparture.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        datePickerDialog.show();
    }

}