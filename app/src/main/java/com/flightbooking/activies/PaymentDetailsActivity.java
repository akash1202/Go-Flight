package com.flightbooking.activies;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;
import com.flightbooking.Utils;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;
import com.flightbooking.model.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentDetailsActivity extends AppCompatActivity {
    TextView tvName,tvDate,tvTotalFair,tvRid;
    EditText etNameonCard,etCardNumber,etExapDate,etCvvumber;
    Button btnCompleteBooking;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    String uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);


        getSupportActionBar().setTitle("Payment Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_name", "");
        etNameonCard=(EditText) findViewById(R.id.etNameonCard);
        etCardNumber=(EditText)findViewById(R.id.etCardNumber);
        etExapDate=(EditText)findViewById(R.id.etExapDate);
        etCvvumber=(EditText)findViewById(R.id.etCvvumber);

        tvName=(TextView)findViewById(R.id.tvName);
        tvDate=(TextView)findViewById(R.id.tvDate);
        tvTotalFair=(TextView)findViewById(R.id.tvTotalFair);
        tvRid=(TextView)findViewById(R.id.tvRid);

        tvRid.setText("Route Id:  ROU#"+getIntent().getStringExtra("Id"));
        tvName.setText("Name:  "+getIntent().getStringExtra("passengername"));
        tvDate.setText("Journy Date:  "+getIntent().getStringExtra("journydate"));
        tvTotalFair.setText("Total Fair:  "+getIntent().getStringExtra("totalfair")+"CAD");

        btnCompleteBooking=(Button)findViewById(R.id.btnCompleteBooking);
        btnCompleteBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(PaymentDetailsActivity.this, "Succuslly Received your Payment", Toast.LENGTH_SHORT).show();

                if(etNameonCard.getText().toString().isEmpty()){
                    Toast.makeText(PaymentDetailsActivity.this, "Please Enter name..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etCardNumber.getText().toString().isEmpty()){
                    Toast.makeText(PaymentDetailsActivity.this, "Please Enter Card Number..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etExapDate.getText().toString().isEmpty()){
                    Toast.makeText(PaymentDetailsActivity.this, "Please Enter Exp Date..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etCvvumber.getText().toString().isEmpty()){
                    Toast.makeText(PaymentDetailsActivity.this, "Please Enter Cvv", Toast.LENGTH_SHORT).show();
                    return;
                }

                payment();
            }
        });
    }

    //$query_dis="insert into book(name,cno,xdate,cvv,amount,jdate,email,rid) values('$name','$cno','$xdate','$cvv',
    //'$amount','$jdate','$email','$rid');";

    private void payment() {

        final String rid = getIntent().getStringExtra("Id");
        final String passengername = getIntent().getStringExtra("passengername");
        final String jdate = getIntent().getStringExtra("journydate");
        final String amount = getIntent().getStringExtra("totalfair");

        String name = etNameonCard.getText().toString();
        String cno = etCardNumber.getText().toString();
        String xdate = etExapDate.getText().toString();
        String cvv = etCvvumber.getText().toString();
        progressDialog = new ProgressDialog(PaymentDetailsActivity.this);
        progressDialog.setMessage("Please Wait wr are processing your payment....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.payment(name,cno,xdate,cvv,rid,jdate,amount,uname);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(PaymentDetailsActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PaymentDetailsActivity.this, CustomerDasboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(PaymentDetailsActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PaymentDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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
}