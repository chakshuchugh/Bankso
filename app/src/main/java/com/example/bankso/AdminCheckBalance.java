package com.example.bankso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AdminCheckBalance extends AppCompatActivity {
    EditText name,accountnum;
    TextView checkbal,bal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_check_balance);
        name=findViewById(R.id.name);
        accountnum=findViewById(R.id.accountnumber);
        checkbal=findViewById(R.id.checkbalbtn);
        bal=findViewById(R.id.checkbalance);
        dbManage db=new dbManage(this);

        checkbal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1=name.getText().toString();
                String email=db.getemail(name1);
                String passward=db.getpassward(name1);
                String bal1=db.getAmount(email,passward);
                bal.setText("The current balance is: "+bal1);
                bal.setVisibility(View.VISIBLE);
            }
        });

    }
}