package com.example.bankso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText personName,gender,email,phonenumber,address,passward,confirmpassward,role;
    TextView signup1;
    private dbManage dbmanage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        personName=findViewById(R.id.PersonName);
        gender=findViewById(R.id.Gender);
        email=findViewById(R.id.Email);
        phonenumber=findViewById(R.id.Phonenum);
        address=findViewById(R.id.Address);
        passward=findViewById(R.id.Passward);
        confirmpassward=findViewById(R.id.confirmPassward);
        role=findViewById(R.id.Role);
        signup1=findViewById(R.id.Signup1);

        dbmanage=new dbManage(this);
        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = personName.getText().toString();
                String gend = gender.getText().toString();
                String email1 = email.getText().toString();
                String phonenum1 = phonenumber.getText().toString();
                String address1 = address.getText().toString();
                String passward1 = passward.getText().toString();
                String role1 = role.getText().toString();

                dbmanage.addNewUser(name,gend,email1, phonenum1, address1,passward1,role1);
                Toast.makeText(getApplicationContext(), "Sign up is successful.", Toast.LENGTH_SHORT).show();
                personName.setText("");
                gender.setText("");
                email.setText("");
                phonenumber.setText("");
                address.setText("");
                passward.setText("");
                confirmpassward.setText("");
                role.setText("");

                Intent i=new Intent(Signup.this,MainActivity.class);
                startActivity(i);

            }
        });
    }
}