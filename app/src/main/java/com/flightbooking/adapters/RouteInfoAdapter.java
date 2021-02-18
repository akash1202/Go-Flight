package com.flightbooking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.flightbooking.R;
import com.flightbooking.model.DummyPojo;
import com.flightbooking.model.RouteInfoPojo;

import java.util.List;

public class RouteInfoAdapter extends BaseAdapter {
    List<RouteInfoPojo> routeInfoPojo;
    Context cnt;

    public RouteInfoAdapter(List<RouteInfoPojo> routeInfoPojo, Context cnt) {
        this.routeInfoPojo = routeInfoPojo;
        this.cnt = cnt;
    }

    @Override
    public int getCount() {
        return routeInfoPojo.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_route_info, null);

        TextView tvTimings = (TextView) obj2.findViewById(R.id.tvTimings);
        tvTimings.setText(routeInfoPojo.get(pos).getFrmtim()+" - "+routeInfoPojo.get(pos).getTotim());

        TextView tvDay = (TextView) obj2.findViewById(R.id.tvDay);
        tvDay.setText(routeInfoPojo.get(pos).getTdays());

        TextView tvPrice = (TextView) obj2.findViewById(R.id.tvPrice);
        tvPrice.setText(routeInfoPojo.get(pos).getPrice()+"CAD");

        TextView tvSourceDest = (TextView) obj2.findViewById(R.id.tvSourceDest);
        tvSourceDest.setText(routeInfoPojo.get(pos).getSource() +" - "+routeInfoPojo.get(pos).getDestination());

        TextView tvoneway = (TextView) obj2.findViewById(R.id.tvoneway);
        tvoneway.setText(routeInfoPojo.get(pos).getType());

        TextView tvAirways = (TextView) obj2.findViewById(R.id.tvAirways);
        tvAirways.setText(routeInfoPojo.get(pos).getAirways());

        Button btnEdit=(Button)obj2.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cnt, "Comin Soon", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnDelete=(Button)obj2.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cnt, "Comin Soon", Toast.LENGTH_SHORT).show();
            }
        });

        return obj2;
    }


}