package com.flightbooking.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.flightbooking.R;
import com.flightbooking.model.BookingsPojo;

import java.util.List;

public class MyBookingsFragmentAdapter extends BaseAdapter {
    List<BookingsPojo> bookingsPojos;
    Context cnt;
    String imgUrl="http://bookingflight.info/flight/";


    public MyBookingsFragmentAdapter(List<BookingsPojo> bookingsPojos, Context cnt) {
        this.bookingsPojos = bookingsPojos;
        this.cnt = cnt;

       /* this.searchhotel=hotelInfo;
        this.cnt = cnt;
        this.hotelInfo = new ArrayList<HotelInfoPojo>();
        this.hotelInfo.addAll(hotelInfo);*/
    }

    @Override
    public int getCount() {
        return bookingsPojos.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_my_bookings, null);

        TextView tvTimings = (TextView) obj2.findViewById(R.id.tvTimings);
        tvTimings.setText(bookingsPojos.get(pos).getFrmtim()+" - "+bookingsPojos.get(pos).getTotim());

        TextView tvDay = (TextView) obj2.findViewById(R.id.tvDay);
        tvDay.setText(bookingsPojos.get(pos).getTdays());

        TextView tvPrice = (TextView) obj2.findViewById(R.id.tvPrice);
        tvPrice.setText(bookingsPojos.get(pos).getTotal()+"CAD");

        TextView tvSourceDest = (TextView) obj2.findViewById(R.id.tvSourceDest);
        tvSourceDest.setText(bookingsPojos.get(pos).getSource() +" - "+bookingsPojos.get(pos).getDestination());

        TextView tvoneway = (TextView) obj2.findViewById(R.id.tvoneway);
        tvoneway.setText(bookingsPojos.get(pos).getType());

        TextView tvAirways = (TextView) obj2.findViewById(R.id.tvAirways);
        tvAirways.setText(bookingsPojos.get(pos).getAirways());


        TextView tvdate = (TextView) obj2.findViewById(R.id.tvdate);
        tvdate.setText(bookingsPojos.get(pos).getDat());

        TextView tvbid = (TextView) obj2.findViewById(R.id.tvbid);
        tvbid.setText("Booking Id : #FB"+bookingsPojos.get(pos).getBid());

        CardView cd_bookings=(CardView) obj2.findViewById(R.id.cd_bookings);
        cd_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, MyTicketDetailsActivity.class);
                intent.putExtra("name",bookingsPojos.get(pos).getName());
                intent.putExtra("bid",bookingsPojos.get(pos).getBid());

                intent.putExtra("time",bookingsPojos.get(pos).getFrmtim()+" - "+bookingsPojos.get(pos).getTotim());
                intent.putExtra("type",bookingsPojos.get(pos).getType());
                intent.putExtra("place",bookingsPojos.get(pos).getSource()+" - "+bookingsPojos.get(pos).getDestination());
                intent.putExtra("air",bookingsPojos.get(pos).getAirways());

                intent.putExtra("stops",bookingsPojos.get(pos).getStops());
                intent.putExtra("lay",bookingsPojos.get(pos).getLayour());
                intent.putExtra("extra",bookingsPojos.get(pos).getExtra());
                intent.putExtra("total",bookingsPojos.get(pos).getTotal());
                cnt.startActivity(intent);
            }
        });


        return obj2;
    }



}