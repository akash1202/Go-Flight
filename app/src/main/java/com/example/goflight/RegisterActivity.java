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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private EditText edtFirstName,edtLastName,edtEmail,edtPassword;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Button btnRegister;
    private TextView txtLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance();
        edtFirstName=findViewById(R.id.signup_edt_firstname);
        edtLastName=findViewById(R.id.signup_edt_lastname);
        edtEmail=findViewById(R.id.signup_edt_email);
        edtPassword=findViewById(R.id.signup_edt_password);
        txtLogin=findViewById(R.id.tv_register_login);
        btnRegister=findViewById(R.id.signup_btn);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regUser();
            }
        });


        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(objIntent);
            }
        });

    }

    private void regUser(){
        boolean error=false;
        final String firstname=edtFirstName.getText().toString();
        final String lastname=edtLastName.getText().toString();
        final String email=edtEmail.getText().toString();
        String password=edtPassword.getText().toString();


        if(firstname.length()==0){
            edtFirstName.setError("Name required...");
            edtFirstName.requestFocus();
            error=true;
        }else{
            edtFirstName.setError(null);

        }
        if(lastname.length()==0){
            edtLastName.setError("Name required...");
            edtLastName.requestFocus();
            error=true;
        }else{
            edtLastName.setError(null);

        }
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

        if(password.length()<8){
            edtPassword.setError("min 8 char password required...");
            edtPassword.requestFocus();
            error=true;
        }else {
            edtPassword.setError(null);
        }


        if(!error){

            //Add to Firebase

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                            // Create a new user with a first and last name
                            Map<String, Object> user = new HashMap<>();
                            user.put("firstname", firstname);
                            user.put("lastname", lastname);
                            user.put("email", email);
                            user.put("password", password);

                            // Add a new document with a generated ID

                            db.getReference("users")
                                    .child(mAuth.getUid())
                                    .setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){
                                                Intent objIntent=new Intent(RegisterActivity.this,CustomerList.class);
                                                startActivity(objIntent);
                                                finish();
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this,"Error Creating User :: "+e.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this,"Unable to get the Location and Register User...",Toast.LENGTH_LONG).show();
                        }



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this,"Auth Error ::"+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }



}
