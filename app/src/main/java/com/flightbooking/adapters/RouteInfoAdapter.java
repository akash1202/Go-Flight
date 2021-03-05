package com.flightbooking.adapters;

import android.app.ProgressDialog;
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
import com.flightbooking.activies.AdminDashboardActivity;
import com.flightbooking.activies.EditRouteActivity;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;
import com.flightbooking.model.ResponseData;
import com.flightbooking.model.RouteInfoPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
               // Toast.makeText(cnt, "Comin Soon", Toast.LENGTH_SHORT).show();


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


                Intent intent=new Intent(cnt, EditRouteActivity.class);
                intent.putExtra("source",routeInfoPojo.get(pos).getSource());
                intent.putExtra("destination",routeInfoPojo.get(pos).getDestination());
                intent.putExtra("airport",routeInfoPojo.get(pos).getAirport());
                intent.putExtra("airways",routeInfoPojo.get(pos).getAirways());
                intent.putExtra("frmtim",routeInfoPojo.get(pos).getFrmtim());
                intent.putExtra("totim",routeInfoPojo.get(pos).getTotim());
                intent.putExtra("tdays",routeInfoPojo.get(pos).getTdays());
                intent.putExtra("type",routeInfoPojo.get(pos).getType());
                intent.putExtra("stops",routeInfoPojo.get(pos).getStops());
                intent.putExtra("layour",routeInfoPojo.get(pos).getLayour());
                intent.putExtra("rid",routeInfoPojo.get(pos).getRid());
                intent.putExtra("price",routeInfoPojo.get(pos).getPrice());
                cnt.startActivity(intent);
            }
        });

        Button btnDelete=(Button)obj2.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(cnt, "Comin Soon", Toast.LENGTH_SHORT).show();
                deleteRoute(routeInfoPojo.get(pos).getRid());

            }
        });

        return obj2;
    }
    ProgressDialog progressDialog;
    public void deleteRoute(String rid){
        progressDialog = new ProgressDialog(cnt);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.deleteroute(rid);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(cnt,"Server issue",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(cnt, AdminDashboardActivity.class);
                    cnt.startActivity(intent);
                    Toast.makeText(cnt," Deleted successfully",Toast.LENGTH_SHORT).show();

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