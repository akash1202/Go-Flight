package com.flightbooking.activies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;
import com.flightbooking.model.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    EditText et_fname,et_lname,et_phno,et_EmailID,et_password;
    Button btn_reg;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().setTitle("Customer Registration");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_fname=(EditText)findViewById(R.id.et_fname);
        et_lname=(EditText)findViewById(R.id.et_lname);
        et_phno=(EditText)findViewById(R.id.et_phno);
        et_EmailID=(EditText)findViewById(R.id.et_EmailID);
        et_password=(EditText)findViewById(R.id.et_password);

        btn_reg=(Button)findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_fname.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter Firstname..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_lname.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter Lastname..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_phno.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter Mobile no..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(et_phno.getText().toString().length() < 9){
                    Toast.makeText(RegistrationActivity.this, "Phone no Should be 10 digits.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(et_EmailID.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter Email Id..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_password.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter Password..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(et_password.getText().length()<5){
                    Toast.makeText(RegistrationActivity.this, "Password length min 6 charaters", Toast.LENGTH_SHORT).show();
                    return;
                }

             /*   if(et_password.getText().toString().length() >5){
                    Toast.makeText(RegistrationActivity.this, "Password Should be 6 digits", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                customerRegistration();
            }
        });
    }
    public boolean validCellPhone(String number)
    {
        return android.util.Patterns.PHONE.matcher(number).matches();
    }
    private void customerRegistration() {
        String fname = et_fname.getText().toString();
        String lname = et_lname.getText().toString();
        String phone = et_phno.getText().toString();
        String email = et_EmailID.getText().toString();
        String pass = et_password.getText().toString();

        progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.customer_registration(fname,lname,email,pass,phone);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
               // Log.d(response.body()+"")
                if (response.body().status.equals("true")) {
                    Toast.makeText(RegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(RegistrationActivity.this, CustomerLoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegistrationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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