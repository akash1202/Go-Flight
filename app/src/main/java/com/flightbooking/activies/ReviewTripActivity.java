package com.flightbooking.activies;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;
import com.flightbooking.Utils;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;
import com.flightbooking.model.ResponseData;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewTripActivity extends AppCompatActivity {
    TextView tvSourceDest, tvTimings, tvDay, tvAirways, tvPrice, tvCabineconomy, tvCancle, tvAdults, tvChildrens, tvChooseDate, tvTotalFair;
    Button btnCheckout;
    EditText etName, etPassportNumber;
    Spinner spinPassCountry, spinExtraLugguage;
    int mYear, mMonth, mDay;
    String DAY, MONTH, YEAR;
    String totalmoney, uname;
    int adultmoney = 0;
    int childrenmoney = 0;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_trip);

        getSupportActionBar().setTitle("Booking Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_name", "");

        etName = (EditText) findViewById(R.id.etName);
        etPassportNumber = (EditText) findViewById(R.id.etPassportNumber);

        tvChooseDate = (TextView) findViewById(R.id.tvChooseDate);

        spinPassCountry = (Spinner) findViewById(R.id.spinPassCountry);
        spinExtraLugguage = (Spinner) findViewById(R.id.spinExtraLugguage);

        tvSourceDest = (TextView) findViewById(R.id.tvSourceDest);
        tvTimings = (TextView) findViewById(R.id.tvTimings);
        tvDay = (TextView) findViewById(R.id.tvDay);
        tvAirways = (TextView) findViewById(R.id.tvAirways);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvCabineconomy = (TextView) findViewById(R.id.tvCabineconomy);
        tvCancle = (TextView) findViewById(R.id.tvCancle);
        tvTotalFair = (TextView) findViewById(R.id.tvTotalFair);

        tvAdults = (TextView) findViewById(R.id.tvAdults);
        tvChildrens = (TextView) findViewById(R.id.tvChildrens);


        adultmoney = Integer.parseInt(getIntent().getStringExtra("adult")) * Integer.parseInt(getIntent().getStringExtra("price"));
        childrenmoney = Integer.parseInt(getIntent().getStringExtra("children")) * Integer.parseInt(getIntent().getStringExtra("price"));
        totalmoney = (adultmoney + childrenmoney) + "";
        //Toast.makeText(this, ""+childrenmoney, Toast.LENGTH_SHORT).show();
        tvAdults.setText("Adult:    -:    " + getIntent().getStringExtra("adult") + "   *   " + getIntent().getStringExtra("price") + "  =    " + adultmoney + "$");
        tvChildrens.setText("Childrens:    -:    " + getIntent().getStringExtra("children") + "   *   " + getIntent().getStringExtra("price") + "  =    " + childrenmoney + "$");
        tvTotalFair.setText("Total Fair:  " + totalmoney + "$");

        //Toast.makeText(this, ""+getIntent().getStringExtra("rid"), Toast.LENGTH_SHORT).show();

        tvSourceDest.setText(getIntent().getStringExtra("sourcedest"));
        tvTimings.setText(getIntent().getStringExtra("timings"));
        tvDay.setText(getIntent().getStringExtra("day"));
        tvAirways.setText(getIntent().getStringExtra("airways"));
        tvPrice.setText(getIntent().getStringExtra("price") + "$");
        tvCabineconomy.setText("Economy Type:  " + getIntent().getStringExtra("classtype"));

        tvChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker();
            }
        });

        btnCheckout = (Button) findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etName.getText().toString().isEmpty()) {
                    Toast.makeText(ReviewTripActivity.this, "Please Enter name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (etPassportNumber.getText().toString().isEmpty()) {
                    Toast.makeText(ReviewTripActivity.this, "Passport Number Should Not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (tvChooseDate.getText().toString().isEmpty()) {
                    Toast.makeText(ReviewTripActivity.this, "Please chose date", Toast.LENGTH_SHORT).show();
                    return;
                }
//                Intent intent = new Intent(ReviewTripActivity.this, PaymentDetailsActivity.class);
//                intent.putExtra("passengername",etName.getText().toString());
//                intent.putExtra("journydate",tvChooseDate.getText().toString());
//                intent.putExtra("totalfair",totalmoney);
//                intent.putExtra("Id",getIntent().getStringExtra("rid"));
//                startActivity(intent);
                  flightBooking();
            }
        });
    }

    ProgressDialog progressDialog;

    private void flightBooking() {
        final String rid = getIntent().getStringExtra("rid");

        String name = etName.getText().toString();
        String passportno = etPassportNumber.getText().toString();
        String passcountry = spinPassCountry.getSelectedItem().toString();
        String date = tvChooseDate.getText().toString();
        String extra = spinExtraLugguage.getSelectedItem().toString();
        String economy = getIntent().getStringExtra("classtype");
        String childe = getIntent().getStringExtra("children");
        String adult = getIntent().getStringExtra("adult");
        final String total = totalmoney;

        progressDialog = new ProgressDialog(ReviewTripActivity.this);
        progressDialog.setMessage("Please Wait we are rediricting to Payment....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.booking(rid, name, passportno, passcountry, date, extra, economy,
                childe, adult, total, uname);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if (response.body().status.equals("true")) {
                    //Toast.makeText(ReviewTripActivity.this, rid, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ReviewTripActivity.this, PaymentDetailsActivity.class);
                    intent.putExtra("passengername",etName.getText().toString());
                    intent.putExtra("journydate",tvChooseDate.getText().toString());
                    intent.putExtra("totalfair",total);
                    intent.putExtra("Id",rid);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(ReviewTripActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ReviewTripActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void datepicker() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        DAY = dayOfMonth + "";
                        MONTH = monthOfYear + 1 + "";
                        YEAR = year + "";

                        tvChooseDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        datePickerDialog.show();
    }

}