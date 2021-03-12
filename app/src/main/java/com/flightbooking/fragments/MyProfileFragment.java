package com.flightbooking.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.flightbooking.R;
import com.flightbooking.Utils;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;
import com.flightbooking.model.MyProfilePojo;
import com.flightbooking.model.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileFragment extends Fragment {
    EditText et_fname,et_lname,et_phno,et_EmailID,et_password;
    Button btnUpdate;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    List<MyProfilePojo> myProfilePojo;
    ResponseData responseData;
    View view;



    public static MyProfileFragment profileFragment() {
        MyProfileFragment fragment = new MyProfileFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_my_profile, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Profile");

        et_fname=(EditText)view.findViewById(R.id.et_fname);
        et_lname=(EditText)view.findViewById(R.id.et_lname);
        et_phno=(EditText)view.findViewById(R.id.et_phno);
        et_EmailID=(EditText)view.findViewById(R.id.et_EmailID);
        et_password=(EditText)view.findViewById(R.id.et_password);
        btnUpdate=(Button)view.findViewById(R.id.btnUpdate);

        getMyProfile();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
                getFragmentManager().beginTransaction().detach(profileFragment()).attach(profileFragment()).commit();
            }
        });


        return view;
    }

    public void getMyProfile(){
        sharedPreferences = getContext().getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        String session = sharedPreferences.getString("user_name", "def-val");
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<MyProfilePojo>> call = service.getProfile(session);
        call.enqueue(new Callback<List<MyProfilePojo>>() {
            @Override
            public void onResponse(Call<List<MyProfilePojo>> call, Response<List<MyProfilePojo>> response) {
                progressDialog.dismiss();
                myProfilePojo = response.body();
                MyProfilePojo myprofile = myProfilePojo.get(0);

                et_fname.setText(myprofile.getFirstname());
                et_lname.setText(myprofile.getLastname());
                et_phno.setText(myprofile.getPhone());
                et_EmailID.setText(myprofile.getEmail());
                et_password.setText(myprofile.getPass());
            }

            @Override
            public void onFailure(Call<List<MyProfilePojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void updateProfile() {
        String firstname = et_fname.getText().toString();
        String lastname = et_lname.getText().toString();
        String phone = et_phno.getText().toString();
        String email = et_EmailID.getText().toString();
        String pass = et_password.getText().toString();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.update_profile(firstname,lastname,email,pass,phone);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(getContext(), response.body().message, Toast.LENGTH_LONG).show();
                    //Intent intent=new Intent(getContext(), MyProfileFragment.class);
                    //startActivity(intent);
                    //finish();

                } else {
                    Toast.makeText(getContext(), response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}