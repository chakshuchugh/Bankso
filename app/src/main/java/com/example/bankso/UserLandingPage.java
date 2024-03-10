package com.example.bankso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserLandingPage extends AppCompatActivity {
    TextView username,cardholdername,accountnumber,checkbalance;
    TextView deposit,withdraw,checkBalance1,history,history1;
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landing_page);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String passward=intent.getStringExtra("passward");
        username=findViewById(R.id.username);
        cardholdername=findViewById(R.id.cardholdername);
        accountnumber=findViewById(R.id.accountnumber);
        deposit=findViewById(R.id.Deposit);
        checkbalance=findViewById(R.id.checkBalance);
        history=findViewById(R.id.History);

        listview=findViewById(R.id.history1);
        checkBalance1=findViewById(R.id.checkbalance1);
        withdraw=findViewById(R.id.Withdraw);
        dbManage db=new dbManage(getApplicationContext());
        String name=db.getName(email,passward);
        String accountnumber1=db.getaccountnumber(email,passward);
        username.setText(name);
        cardholdername.setText(name);
        accountnumber.setText(accountnumber1);

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddAmount.class);
                intent.putExtra("email",email);
                intent.putExtra("passward",passward);
                startActivity(intent);
            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Withdrawamount.class);
                intent.putExtra("email",email);
                intent.putExtra("passward",passward);
                startActivity(intent);
            }
        });

        checkbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bal=db.getAmount(email,passward);
                checkBalance1.setText("Your Account Balance is: Rs "+bal);
                boolean b=true;
                if(b){
                    checkBalance1.setVisibility(view.VISIBLE);
                    b=false;
                }
//                else if(b==false){
//                    checkBalance1.setVisibility(view.INVISIBLE);
//                    b=true;
//                }
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=db.getid(email,passward);
                ArrayList<HistoryModel> history=db.showData(id);
                ArrayList<String> history1=new ArrayList<>();
                for (int i=0;i<history.size();i++){
                    history1.add((i+1)+"                 "+history.get(i).deposit+"             "+history.get(i).withdraw);
                }
                ArrayAdapter<String> adapter= new ArrayAdapter<>(UserLandingPage.this, R.layout.list_view_style, history1);
                listview.setAdapter(adapter);
                listview.setVisibility(View.VISIBLE);
                Toast.makeText(UserLandingPage.this, "", Toast.LENGTH_SHORT).show();
//                checkBalance1.setText(history.get(1).withdraw);
            }
        });



    }
}