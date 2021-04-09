package com.flightbooking.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.flightbooking.R;
import com.flightbooking.activies.AdminDashboardActivity;
import com.flightbooking.activies.EditHotelActivity;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;
import com.flightbooking.model.HotelInfoPojo;
import com.flightbooking.model.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelInfoAdapter extends BaseAdapter {
    List<HotelInfoPojo> hotelInfo;
    AppCompatActivity cnt;
    //String imgUrl="http://bookingflight.info/flight";
    String imgUrl="https://goflightinfo.000webhostapp.com/flight/";


    public HotelInfoAdapter(List<HotelInfoPojo> hotelInfo, AppCompatActivity cnt) {
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

       // Toast.makeText(cnt,hotelInfo.get(pos).getPhoto().toString(),Toast.LENGTH_LONG).show();

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

        Button btnEdit=(Button)obj2.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(cnt, "Comin Soon", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(cnt, EditHotelActivity.class);
                intent.putExtra("hid",hotelInfo.get(pos).getHid());
                intent.putExtra("name",hotelInfo.get(pos).getName());
                intent.putExtra("city",hotelInfo.get(pos).getCity());
                intent.putExtra("province",hotelInfo.get(pos).getProvince());
                intent.putExtra("country",hotelInfo.get(pos).getCountry());
                intent.putExtra("pcode",hotelInfo.get(pos).getPcode());
                intent.putExtra("price",hotelInfo.get(pos).getPrice());
                intent.putExtra("photo",hotelInfo.get(pos).getPhoto());
                intent.putExtra("web",hotelInfo.get(pos).getWeb());
                cnt.startActivity(intent);
                cnt.finish();
            }
        });

        Button btnDelete=(Button)obj2.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        // Toast.makeText(cnt, "Comin Soon", Toast.LENGTH_SHORT).show();
        deleteHotel(hotelInfo.get(pos).getHid());
        }
        });

        return obj2;
}


        ProgressDialog progressDialog;
public void deleteHotel(String hid){
        progressDialog = new ProgressDialog(cnt);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.deletehotel(hid);
        call.enqueue(new Callback<ResponseData>() {
@Override
public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
        progressDialog.dismiss();
        if(response.body()==null){
        Toast.makeText(cnt,"Server issue",Toast.LENGTH_SHORT).show();
        }else {
        Intent intent=new Intent(cnt, AdminDashboardActivity.class);
        cnt.startActivity(intent);
        Toast.makeText(cnt,"Hotel Removed successfully !!!",Toast.LENGTH_SHORT).show();
        }
        }
@Override
public void onFailure(Call<ResponseData> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(cnt, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
        }
        });
        }

}