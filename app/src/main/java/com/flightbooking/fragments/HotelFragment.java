package com.flightbooking.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.flightbooking.R;
import com.flightbooking.adapters.HotelFragmentAdapter;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;
import com.flightbooking.model.HotelInfoPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelFragment extends Fragment {
    List<HotelInfoPojo> hotelInfo;
    ListView list_view;
    ProgressDialog progressDialog;
    Button btnAddHotel;
    EditText et_search;
    HotelFragmentAdapter hotelFragmentAdapter;
    View view;


    public static HotelFragment hotelFragment() {
        HotelFragment fragment = new HotelFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_hotel, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Hotels");

        list_view = (ListView) view.findViewById(R.id.list_view);
        et_search = (EditText)view.findViewById(R.id.et_search);

        hotelInfo = new ArrayList<>();
        getHotelInfo();

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = et_search.getText().toString().toLowerCase(Locale.getDefault());
                hotelFragmentAdapter.searchHotel(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
        });


        return view;
    }
    public void getHotelInfo() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<HotelInfoPojo>> call = service.gethotel();
        call.enqueue(new Callback<List<HotelInfoPojo>>() {
            @Override
            public void onResponse(Call<List<HotelInfoPojo>> call, Response<List<HotelInfoPojo>> response) {
                progressDialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    hotelInfo = response.body();
                    Log.d("Hotel info---->","response"+hotelInfo.toString());
                    hotelFragmentAdapter=new HotelFragmentAdapter(hotelInfo,getContext());
                    list_view.setAdapter(hotelFragmentAdapter);
                    //list_view.setAdapter(new HotelFragmentAdapter(hotelInfo, getContext()));

                }
            }

            @Override
            public void onFailure(Call<List<HotelInfoPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}