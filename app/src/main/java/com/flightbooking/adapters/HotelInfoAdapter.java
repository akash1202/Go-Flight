package com.flightbooking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flightbooking.R;

import java.util.List;

public class HotelInfoAdapter extends BaseAdapter {
    List<HotelInfoPojo> hotelInfo;
    Context cnt;
    String imgUrl="http://bookingflight.info/flight/";


    public HotelInfoAdapter(List<HotelInfoPojo> hotelInfo, Context cnt) {
        this.hotelInfo = hotelInfo;
        this.cnt = cnt;
    }

    @Override
    public int getCount() {
        return hotelInfo.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_hotel_info, null);

        ImageView hotelImage=(ImageView)obj2.findViewById(R.id.hotelImage);
        Glide.with(cnt).load(imgUrl+hotelInfo.get(pos).getPhoto()).into(hotelImage);

        Toast.makeText(cnt,hotelInfo.get(pos).getPhoto().toString(),Toast.LENGTH_LONG).show();

        TextView tvHotelname = (TextView) obj2.findViewById(R.id.tvHotelname);
        tvHotelname.setText("Name: "+hotelInfo.get(pos).getName());

      //  tvHotelname.setText("Name: "+imgUrl+hotelInfo.get(pos).getPhoto());


        TextView tvCountry = (TextView) obj2.findViewById(R.id.tvCountry);
        tvCountry.setText("Country: "+hotelInfo.get(pos).getCountry());


        TextView tvCityName = (TextView) obj2.findViewById(R.id.tvCityName);
        tvCityName.setText("City: "+hotelInfo.get(pos).getCity());


        TextView tvProvince = (TextView) obj2.findViewById(R.id.tvProvince);
        tvProvince.setText("Province: "+hotelInfo.get(pos).getProvince());


        TextView tvoPstalcode = (TextView) obj2.findViewById(R.id.tvoPstalcode);
        tvoPstalcode.setText("Postal Code: "+hotelInfo.get(pos).getPcode());


        TextView tvPrice = (TextView) obj2.findViewById(R.id.tvPrice);
        tvPrice.setText("Price: "+"CAD"+" $"+hotelInfo.get(pos).getPrice());


        return obj2;
    }


}