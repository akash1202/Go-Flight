package com.flightbooking.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.flightbooking.R;
import com.flightbooking.activies.RegistrationActivity;
import com.flightbooking.model.AvailableFlightsPojo;

import java.util.List;

public class GuestAvailableFlightsAdapter extends BaseAdapter {
    List<AvailableFlightsPojo> availableFlightsPojo;
    Context cnt;
    String adult,children,classtype;

    public GuestAvailableFlightsAdapter(List<AvailableFlightsPojo> availableFlightsPojo, String adult, String children, String classtype, Context cnt) {
        this.availableFlightsPojo = availableFlightsPojo;
        this.adult=adult;
        this.children=children;
        this.classtype=classtype;
        this.cnt = cnt;
    }

    @Override
    public int getCount() {
        return availableFlightsPojo.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int pos, View view, ViewGroup viewGroup) {
        LayoutInflater obj1 = (LayoutInflater) cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View obj2 = obj1.inflate(R.layout.adapter_available_flights, null);

        TextView tvTimings = (TextView) obj2.findViewById(R.id.tvTimings);
        tvTimings.setText(availableFlightsPojo.get(pos).getFrmtim()+" - "+availableFlightsPojo.get(pos).getTotim());

        TextView tvDay = (TextView) obj2.findViewById(R.id.tvDay);
        tvDay.setText(availableFlightsPojo.get(pos).getTdays());

        TextView tvPrice = (TextView) obj2.findViewById(R.id.tvPrice);
        tvPrice.setText(availableFlightsPojo.get(pos).getPrice()+"CAD");

        TextView tvSourceDest = (TextView) obj2.findViewById(R.id.tvSourceDest);
        tvSourceDest.setText(availableFlightsPojo.get(pos).getSource() +" - "+availableFlightsPojo.get(pos).getDestination());

        TextView tvoneway = (TextView) obj2.findViewById(R.id.tvoneway);
        tvoneway.setText(availableFlightsPojo.get(pos).getType());

        TextView tvAirways = (TextView) obj2.findViewById(R.id.tvAirways);
        tvAirways.setText(availableFlightsPojo.get(pos).getAirways());

        Button btnBook=(Button)obj2.findViewById(R.id.btnBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(cnt, RegistrationActivity.class);
                Toast.makeText(cnt,"Please Register In App to Book Flights",Toast.LENGTH_LONG).show();
                cnt.startActivity(intent);


            }
        });


        return obj2;
    }


}