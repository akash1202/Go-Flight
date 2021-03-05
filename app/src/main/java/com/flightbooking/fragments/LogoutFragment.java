package com.flightbooking.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.flightbooking.R;
import com.flightbooking.activies.MainActivity;


public class LogoutFragment extends Fragment {
    View view;
    ImageView img_logout;


    public static LogoutFragment logoutFragment() {
        LogoutFragment fragment = new LogoutFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_logout, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Logout");

        img_logout=(ImageView)view.findViewById(R.id.img_logout);
        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivity.class));
                ((Activity)getActivity()).finish();
            }
        });

        return view;
    }
}