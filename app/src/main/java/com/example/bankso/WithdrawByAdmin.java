package com.example.bankso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WithdrawByAdmin extends AppCompatActivity {
    EditText name,amount,acnumber;
    TextView withdrawbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_by_admin);
        name=findViewById(R.id.name);
        amount=findViewById(R.id.withdraw1);
        acnumber=findViewById(R.id.acnumber);
        withdrawbtn=findViewById(R.id.amountwithdraw);
        dbManage db=new dbManage(this);
        withdrawbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1=name.getText().toString();
                String enteredacnum=acnumber.getText().toString();
                String amount1=amount.getText().toString();
                String acnum=db.getaccountnumber1(name1);
                Toast.makeText(WithdrawByAdmin.this, ""+acnum, Toast.LENGTH_SHORT).show();
                if(acnum.equals(enteredacnum)) {
                    Toast.makeText(WithdrawByAdmin.this, "ac num matched", Toast.LENGTH_SHORT).show();
                    String email=db.getemail(name1);
                    String passward=db.getpassward(name1);
                    String pid=db.getid(email,passward);
                    db.addAmount(pid,"0",amount1 );
                    String newamt= db.getAmount(email,passward);
                    int amount=Integer.parseInt(newamt)-Integer.parseInt(amount1);
                    String namt=Integer.toString(amount);
                    try{
                        boolean deposit=db.updateAmt(namt,email,passward);
                        if(deposit){
                            Toast.makeText(WithdrawByAdmin.this, "Amount deposited", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(WithdrawByAdmin.this, "Amount not deposited", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch(Exception ex){
                        Toast.makeText(WithdrawByAdmin.this, ""+ex, Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(WithdrawByAdmin.this, "Incorrect Passward", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}