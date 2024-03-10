package com.example.bankso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AdminLandingPage extends AppCompatActivity {
    TextView logout,addaccount,username,deposit,withdraw,checkbal,detail,deleteuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing_page);
        logout=findViewById(R.id.signout);
        addaccount=findViewById(R.id.addaccount);
        username=findViewById(R.id.username);
        deposit=findViewById(R.id.textView9);
        withdraw=findViewById(R.id.withdraw);
        checkbal=findViewById(R.id.checkbal);
        deleteuser=findViewById(R.id.textView6);
        detail=findViewById(R.id.details);
        dbManage db=new dbManage(getApplicationContext());
        Intent intent=getIntent();
        String email=intent.getStringExtra("email");
        String passward=intent.getStringExtra("passward");
        username.setText(db.getName(email,passward));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        addaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);
            }
        });
        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DepositByAdmin.class);
                startActivity(intent);
            }
        });
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),WithdrawByAdmin.class);
                startActivity(intent);
            }
        });
        checkbal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AdminCheckBalance.class);
                startActivity(intent);
            }
        });
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),UserDetailByAdmin.class);
                startActivity(intent);
            }
        });
        deleteuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DeleteUserByAdmin.class);
                startActivity(intent);
            }
        });
    }
}