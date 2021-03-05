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

    @GET("/flight/customer_registration.php?")
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

    @GET("/flight/getProfile.php?")
    Call<List<MyProfilePojo>> getProfile(
            @Query("email") String email);

    @GET("/flight/mybookings.php?")
    Call<List<BookingsPojo>> getmybooking(
            @Query("email") String email);

    @GET("/flight/update_profile.php?")
    Call<ResponseData> update_profile(
            @Query("fname") String fname,
            @Query("lname") String lname,
            @Query("email") String email,
            @Query("pass") String pass,
            @Query("phone") String phone);

    @GET("flight/getusers.php?")
    Call<List<MyProfilePojo>> getcustprofile();


    @GET("flight/gethotel.php?")
    Call<List<HotelInfoPojo>> gethotel();

    @GET("flight/getbooking.php?")
    Call<List<BookingsPojo>> getbooking();


    @GET("flight/getroutes.php?")
    Call<List<RouteInfoPojo>> getroutes();


    @GET("flight/searchflight.php?")
    Call<List<AvailableFlightsPojo>> getAvailableFlightRoutes(
            @Query("source") String source,
            @Query("destination") String destination,
            @Query("type") String type);

    @GET("flight/forgotPassword.php")
    Call<ResponseData> forgotPassword(@Query("email") String emailid);

    @Multipart
    @POST("flight/addhotel.php")
    Call<ResponseData> addhotel(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap
    );

    @GET("/flight/booking.php?")
    Call<ResponseData> booking(
            @Query("rid") String rid,
            @Query("name") String name,
            @Query("passno") String passno,
            @Query("passcountry") String passcountry,
            @Query("dat") String dat,
            @Query("extra") String extra,
            @Query("clas") String clas,
            @Query("child") String child,
            @Query("adult") String adult,
            @Query("total") String total,
            @Query("uname") String uname
    );

//name,cno,xdate,cvv,rid,jdate,amount,uname
    @GET("/flight/payment.php?")
    Call<ResponseData> payment(
            @Query("name") String name,
            @Query("cno") String cno,
            @Query("xdate") String xdate,
            @Query("cvv") String cvv,
            @Query("rid") String rid,
            @Query("jdate") String jdate,
            @Query("amount") String amount,
            @Query("uname") String uname

    );

    @GET("/flight/deleteroute.php")
    Call<ResponseData> deleteroute(@Query("rid") String rid);

    @GET("/flight/editroute.php?")
    Call<ResponseData> editroute(
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
            @Query("rid") String rid,
            @Query("price") String price);

}
