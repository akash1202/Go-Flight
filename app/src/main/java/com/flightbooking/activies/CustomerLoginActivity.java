package com.flightbooking.activies;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

public class CustomerLoginActivity extends AppCompatActivity {
    EditText et_USERNAME,et_PWD;
    TextView tv_forgetpwd,tv_reg_here,tvforgot;
    Button btnLogin;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        getSupportActionBar().setTitle("Customer Login");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_USERNAME=(EditText)findViewById(R.id.et_USERNAME);
        et_PWD=(EditText)findViewById(R.id.et_PWD);

        tv_forgetpwd=(TextView)findViewById(R.id.tv_forgetpwd);
        tv_reg_here=(TextView)findViewById(R.id.tv_reg_here);

        tv_reg_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerLoginActivity.this, RegistrationActivity.class));
                finish();
            }
        });

        tv_forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerLoginActivity.this, ForgotPasswordActivity.class));
                    finish();
            }
        });

        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_USERNAME.getText().toString().isEmpty()){
                    Toast.makeText(CustomerLoginActivity.this, "Please Enter Username..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_PWD.getText().toString().isEmpty()){
                    Toast.makeText(CustomerLoginActivity.this, "Please Enter Password..", Toast.LENGTH_SHORT).show();
                    return;
                }
                CustomerloginFunction();
            }
        });
    }

    public  void CustomerloginFunction() {
        pd= new ProgressDialog(CustomerLoginActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.customer_login(et_USERNAME.getText().toString(),et_PWD.getText().toString());

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor et=sharedPreferences.edit();
                    et.putString("user_name",et_USERNAME.getText().toString());
                    et.putBoolean("isCustomer",Boolean.TRUE);
                    et.commit();
                    startActivity(new Intent(CustomerLoginActivity.this, CustomerDasboardActivity.class));
                    finish();
                } else {
                    Log.e("Customer login",response.body().message);
                    Toast.makeText(CustomerLoginActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Log.e("Customer login",t.getCause()+" \n "+t.getMessage());
                Toast.makeText(CustomerLoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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