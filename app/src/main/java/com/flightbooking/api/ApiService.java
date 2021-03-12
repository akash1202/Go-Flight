package com.flightbooking.api;


import com.flightbooking.model.AvailableFlightsPojo;
import com.flightbooking.model.BookingsPojo;
import com.flightbooking.model.HotelInfoPojo;
import com.flightbooking.model.MyProfilePojo;
import com.flightbooking.model.ResponseData;
import com.flightbooking.model.RouteInfoPojo;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;


public interface ApiService {

    @GET("/flight/customer_register.php?")
    Call<ResponseData> customer_registration(
            @Query("fname") String fname,
            @Query("lname") String lname,
            @Query("email") String email,
            @Query("pass") String pass,
            @Query("phone") String phone);

    @GET("/flight/customer_login.php?")
    Call<ResponseData> customer_login(
            @Query("email") String email,
            @Query("pass") String pass);


    @GET("/flight/addHotel.php?")
    Call<ResponseData> addHotel(
            @Query("name") String name,
            @Query("country") String country,
            @Query("provience") String provience,
            @Query("city") String city,
            @Query("code") String code,
            @Query("price") String price);

    @GET("/flight/addroutes.php?")
    Call<ResponseData> addroutes(
            @Query("source") String source,
            @Query("destination") String destination,
            @Query("airport") String airport,
            @Query("airways") String airways,
            @Query("frmtim") String frmtim,
            @Query("totim") String totim,
            @Query("tdays") String tdays,
            @Query("type") String type,
            @Query("stops") String stops,
            @Query("layour") String layour,
            @Query("price") String price);


    @GET("/flight/adminlogin.php?")
    Call<ResponseData> adminlogin(
            @Query("uname") String uname,
            @Query("pass") String pass);


}
