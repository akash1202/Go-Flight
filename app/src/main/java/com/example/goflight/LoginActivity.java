package com.example.goflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail,edtPassword;
    private TextView txtRegister;
    private Button loginBtn,btnRegister;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextView tv_ForgotPassword;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();


        txtRegister=findViewById(R.id.tv_login_reg);
        tv_ForgotPassword=(TextView)findViewById(R.id.tv_ForgotPassword);

        edtEmail=findViewById(R.id.login_edt_email);
        edtPassword=findViewById(R.id.login_edt_password);
        loginBtn=findViewById(R.id.login_btn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(objIntent);

            }
        });



        tv_ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent forgotIntent=new Intent(LoginActivity.this,ResetPassword.class);
                    startActivity(forgotIntent);


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

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        Intent profileIntent=new Intent(LoginActivity.this,CustomerList.class);
                        startActivity(profileIntent);
                    }else{
                        Toast.makeText(LoginActivity.this,"Invalid Login details...",Toast.LENGTH_LONG).show();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this,"Failed to Login.Check Email and Password ",Toast.LENGTH_LONG).show();
                }
            });



//            tv_ForgotPassword.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(LoginActivity.this,ResetPassword.class));
//
//                }
//            });





        }
    }
}
