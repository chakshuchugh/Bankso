package com.example.bankso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    EditText email,passward;
    TextView adminSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        email=findViewById(R.id.Email);
        passward=findViewById(R.id.Passward);
        adminSignIn=findViewById(R.id.AdminLogin);
        adminSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    dbManage db=new dbManage(getApplicationContext());
                    String Email=email.getText().toString();
                    String Passward=passward.getText().toString();
                    boolean ans=db.Adminlogin(Email,Passward);
                    if(ans){
                        Intent intent=new Intent(getApplicationContext(),AdminLandingPage.class);
                        intent.putExtra("email",Email);
                        intent.putExtra("passward",Passward);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(AdminLogin.this, "wrong email or passward", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception ex){
                    Toast.makeText(AdminLogin.this, ""+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}