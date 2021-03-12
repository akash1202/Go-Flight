package com.flightbooking.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.flightbooking.R;
import com.flightbooking.Utils;
import com.flightbooking.adapters.MyBookingsFragmentAdapter;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;
import com.flightbooking.model.BookingsPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookingsFragment extends Fragment {
    List<BookingsPojo> hotelInfo;
    ListView list_view;
    ProgressDialog progressDialog;
    Button btnAddHotel;
    MyBookingsFragmentAdapter myBookingsFragmentAdapter;
    View view;
    SharedPreferences sharedPreferences;


    public static MyBookingsFragment myBookingsFragment() {
        MyBookingsFragment fragment = new MyBookingsFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_mybookings, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Bookings");

        list_view = (ListView) view.findViewById(R.id.list_view);

        hotelInfo = new ArrayList<>();
        getMyBookings();



        return view;
    }
    public void getMyBookings() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        sharedPreferences = getContext().getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        String session = sharedPreferences.getString("user_name", "def-val");
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<BookingsPojo>> call = service.getmybooking(session);
        call.enqueue(new Callback<List<BookingsPojo>>() {
            @Override
            public void onResponse(Call<List<BookingsPojo>> call, Response<List<BookingsPojo>> response) {
                progressDialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    hotelInfo = response.body();
                    myBookingsFragmentAdapter=new MyBookingsFragmentAdapter(hotelInfo,getContext());
                    list_view.setAdapter(myBookingsFragmentAdapter);
                    //list_view.setAdapter(new HotelFragmentAdapter(hotelInfo, getContext()));

                }
            }

            @Override
            public void onFailure(Call<List<BookingsPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}