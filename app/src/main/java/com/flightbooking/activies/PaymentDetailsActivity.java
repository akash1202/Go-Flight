package com.flightbooking.activies;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.flightbooking.R;
import com.flightbooking.Utils;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;
import com.flightbooking.model.ResponseData;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.vinaygaba.creditcardview.CreditCardView;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentDetailsActivity extends AppCompatActivity {
    TextView tvName,tvDate,tvTotalFair,tvRid;
    EditText etNameonCard,etCardNumber,etExapDate,etCvvumber;
    Button btnCompleteBooking;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    CreditCardView debitCardView;
    CardView debitLayout;
    RadioButton radioDebit,radioPaypal;
    String uname,paypalCreateTime,paypalPaymentID,paypalStatus;
    private static PayPalConfiguration paypalconfig=new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .merchantName("Go Flight")
            .merchantPrivacyPolicyUri(Uri.parse("https://goflightinfo.000webhostapp.com/privacy-policy/"))
            .merchantUserAgreementUri(Uri.parse("https://goflightinfo.000webhostapp.com/privacy-policy/"))
            .clientId("AX8AoTzYqoQzwkrIe2A0pv-X1IEkP30C5DRZ5dPE9HuS7Zctn6rIdXk9u9nk6b5AmsEaGZYcXUELuZFE");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        getSupportActionBar().setTitle("Payment Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        debitCardView=findViewById(R.id.debitCardView);
        debitCardView.setBrandLogo(R.drawable.logo);

        etNameonCard=(EditText) findViewById(R.id.etNameonCard);
        etCardNumber=(EditText)findViewById(R.id.etCardNumber);
        etExapDate=(EditText)findViewById(R.id.etExapDate);
        etCvvumber=(EditText)findViewById(R.id.etCvvumber);

        radioDebit=findViewById(R.id.payOptionDebit);
        radioPaypal=findViewById(R.id.payOPtionPaypal);

        tvName=(TextView)findViewById(R.id.tvName);
        tvDate=(TextView)findViewById(R.id.tvDate);
        tvTotalFair=(TextView)findViewById(R.id.tvTotalFair);
        tvRid=(TextView)findViewById(R.id.tvRid);
        debitLayout=findViewById(R.id.debitCardLayout);

        btnCompleteBooking=(Button)findViewById(R.id.btnCompleteBooking);
        showDebitOPtion();

        radioDebit.setChecked(true);
        radioPaypal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    showPaypalPayOption();
                }
            }
        });
        radioDebit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    showDebitOPtion();
                }
            }
        });

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_name", "");
        debitCardView.setCardName(getIntent().getStringExtra("passengername"));
        debitCardView.setExpiryDate("MM/YY");

        tvRid.setText("Route Id:  ROU#"+getIntent().getStringExtra("Id"));
        tvName.setText("Name:  "+getIntent().getStringExtra("passengername"));
        tvDate.setText("Journy Date:  "+getIntent().getStringExtra("journydate"));
        tvTotalFair.setText("Total Fair:  "+getIntent().getStringExtra("totalfair"));
        btnCompleteBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(PaymentDetailsActivity.this, "Succuslly Received your Payment", Toast.LENGTH_SHORT).show();

                if(debitCardView.getCardName().toString().isEmpty()){
                    Toast.makeText(PaymentDetailsActivity.this, "Please Enter name..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(debitCardView.getCardNumber().toString().isEmpty()){
                    Toast.makeText(PaymentDetailsActivity.this, "Please Enter Card Number..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(debitCardView.getExpiryDate().toString().isEmpty()){
                    Toast.makeText(PaymentDetailsActivity.this, "Please Enter Exp Date..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(radioDebit.isChecked()){
                    payment(); //pay with debit card
                }else{
                    payNowWithPaypal(); //pay with credit card
                }
            }
        });
    }

    public void showPaypalPayOption(){
            debitLayout.setVisibility(View.GONE);
            btnCompleteBooking.setText("Continue Booking");
    }
    public void showDebitOPtion(){
        debitLayout.setVisibility(View.VISIBLE);
        btnCompleteBooking.setText("Complete Booking");
    }

    //$query_dis="insert into book(name,cno,xdate,cvv,amount,jdate,email,rid) values('$name','$cno','$xdate','$cvv',
    //'$amount','$jdate','$email','$rid');";

    private void payment() { //debit card payment
        final String rid = getIntent().getStringExtra("Id");
        final String passengername = getIntent().getStringExtra("passengername");
        final String jdate = getIntent().getStringExtra("journydate");
        final String amount = getIntent().getStringExtra("totalfair");
/*
        String name = etNameonCard.getText().toString();
        String cno = etCardNumber.getText().toString();
        String xdate = etExapDate.getText().toString();
        String cvv = etCvvumber.getText().toString();*/

        String name = debitCardView.getCardName();
        String cno = debitCardView.getCardNumber();
        String xdate = debitCardView.getExpiryDate();
        String cvv="210";
        debitCardView.setIsCvvEditable(true);


        progressDialog = new ProgressDialog(PaymentDetailsActivity.this);
        progressDialog.setMessage("Please Wait we are processing your payment....");
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

    private void payNowWithPaypal(){ //credit card & paypal
        Intent intent=new Intent(PaymentDetailsActivity.this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,paypalconfig);
        startService(intent);
        //PAYMENT_INTENT_SALE will cause the payment to complete immediately
        // change PAYMENT_INTENT_AUTHORIZE to only authorize payment and capture funds later
        //change PAYMENT_INTENT_ORDER to create a payment for
        PayPalPayment payPalPayment=new PayPalPayment(new BigDecimal(getIntent().getStringExtra("totalfair")),
                "CAD","Akash Anaghan",PayPalPayment.PAYMENT_INTENT_ORDER);
        Intent intent1=new Intent(this, PaymentActivity.class);
        intent1.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,paypalconfig);
        intent1.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        startActivityForResult(intent1,0);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            PaymentConfirmation paymentConfirmation=data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if(paymentConfirmation!=null){
                try{
                    Log.i("Paypal response",paymentConfirmation.toJSONObject().toString(4));

                    Toast.makeText(this, paymentConfirmation.toJSONObject().toString(4), Toast.LENGTH_LONG).show();
                    if(isPaymentSuccess(paymentConfirmation.toJSONObject())){
                        finish();
                    }
                    //Toast.makeText(this, "Payment Done !!!", Toast.LENGTH_LONG).show();
                    //setText(paymentConfirmation.toJSONObject().toString(4));
                    //TODO: send 'confirmation to server for verification'
                }catch (JSONException e){
                    Log.e("Paypal JSONError:",e.toString());
                }
            }
        }else if(resultCode==Activity.RESULT_CANCELED){
            Log.i("Paypal ","User has canceled");
        }else if(resultCode==PaymentActivity.RESULT_EXTRAS_INVALID){
            Log.i("Paypal Example","An invalid Paypal Configuration");
        }
    }

    boolean isPaymentSuccess(JSONObject response){
        try {
            JSONObject clientData= response.getJSONObject("client");
            JSONObject responseData=response.getJSONObject("response");
            paypalCreateTime=responseData.getString("create_time");
            paypalPaymentID=responseData.getString("id");
            paypalStatus=responseData.getString("state");
            if(paypalStatus.equalsIgnoreCase("approved")){
                Toast.makeText(getApplicationContext(), "Payment Successful !!!", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
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