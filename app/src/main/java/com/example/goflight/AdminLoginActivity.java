package com.example.goflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginActivity extends AppCompatActivity {
    private EditText edtEmail,edtPassword;
    private Button loginBtn;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        mAuth=FirebaseAuth.getInstance();

        edtEmail=findViewById(R.id.adm_login_edt_email);
        edtPassword=findViewById(R.id.adm_login_edt_password);
        loginBtn=findViewById(R.id.adm_login_btn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    private void checkLogin(){
        String email=edtEmail.getText().toString();
        String password=edtPassword.getText().toString();
        boolean error=false;


        if(email.length()==0){
            edtEmail.setError("Email required...");
            edtEmail.requestFocus();
            error=true;
        }else{

            if(email.trim().matches(emailPattern)){
                edtEmail.setError(null);
            }else{
                edtEmail.requestFocus();
                edtEmail.setError("Enter valid Email...");
                error=true;
            }


        }

        if(password.length()==0){
            edtPassword.setError("Password required...");
            edtPassword.requestFocus();
            error=true;
        }else {
            edtPassword.setError(null);

        }

        if(!error){

            if(email.equals("Access@admin.com") && password.equals("admin1234")){

                mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        Intent objIntent=new Intent(AdminLoginActivity.this,MainActivity.class);
                        startActivity(objIntent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(AdminLoginActivity.this,"Invalid Login Details",Toast.LENGTH_LONG).show();
                    }
                });

            }else{
                Toast.makeText(AdminLoginActivity.this,"Invalid Login Details...",Toast.LENGTH_LONG).show();
            }

        }
    }
}
