package com.example.bankso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserLogin extends AppCompatActivity {
    EditText email,passward;
    TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        email=findViewById(R.id.Email);
        passward=findViewById(R.id.Passward);
        signIn=findViewById(R.id.UserLogin);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dbManage db = new dbManage(getApplicationContext());
                    String Email = email.getText().toString();
                    String Passward = passward.getText().toString();

                    boolean ans = db.login(Email, Passward);
                    if (ans) {
                        Toast.makeText(getApplicationContext(), "Login is successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),UserLandingPage.class);
                        intent.putExtra("email",Email);
                        intent.putExtra("passward",Passward);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Email or Passward", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(UserLogin.this, ""+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}