package com.example.bankso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView adminlogin,userlogin,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adminlogin=findViewById(R.id.AdminLogin);
        userlogin=findViewById(R.id.UserLogin);
        signup=findViewById(R.id.signup);

        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AdminLogin.class);
                startActivity(intent);
            }
        });

        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,UserLogin.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Signup.class);
                startActivity(intent);
            }
        });
    }
}