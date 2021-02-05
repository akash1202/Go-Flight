package com.example.goflight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class NavUserActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnRegister,btnLogin,btnAdminLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_user);

        mAuth=FirebaseAuth.getInstance();


       /* if(mAuth.getCurrentUser()!=null){

            if(mAuth.getUid().equals("1EMmVoZ9EshCMGvZmUeTJBad5Ec2")){
                //if Admin Logs IN
                Intent objIntent=new Intent(NavUserActivity.this,AdminDashboardActivity.class);
                startActivity(objIntent);
                finish();
            }else{
                //if the User Already Logged In then move to FrontPage Activity
                Intent objIntent=new Intent(NavUserActivity.this,FrongPageActivity.class);
                startActivity(objIntent);
                finish();
            }


        }*/
        btnRegister=findViewById(R.id.nav_btn_register);
        btnLogin=findViewById(R.id.nav_btn_login);
        btnAdminLogin=findViewById(R.id.nav_btn_admin_login);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent=new Intent(NavUserActivity.this,RegisterActivity.class);
                startActivity(objIntent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent=new Intent(NavUserActivity.this,LoginActivity.class);
                startActivity(objIntent);
                finish();
            }
        });

        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent=new Intent(NavUserActivity.this,AdminLoginActivity.class);
                startActivity(objIntent);
                finish();
            }
        });
    }
}
