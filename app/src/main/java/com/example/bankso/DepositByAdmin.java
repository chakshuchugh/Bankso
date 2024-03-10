package com.example.bankso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DepositByAdmin extends AppCompatActivity {
    EditText depositamt,account_number,name1;
    TextView depositbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_by_admin);
        depositamt=findViewById(R.id.deposit1);
        account_number=findViewById(R.id.depositPassword);
        depositbtn=findViewById(R.id.amtdeposit);
        name1=findViewById(R.id.name);

        dbManage db=new dbManage(this);
        depositbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=name1.getText().toString();
                String enteredacnum=account_number.getText().toString();
                String amount=depositamt.getText().toString();
                String acnum=db.getaccountnumber1(name);
                Toast.makeText(DepositByAdmin.this, ""+acnum, Toast.LENGTH_SHORT).show();
                if(acnum.equals(enteredacnum)) {
                    Toast.makeText(DepositByAdmin.this, "ac num matched", Toast.LENGTH_SHORT).show();
                    String email=db.getemail(name);
                    String passward=db.getpassward(name);
                    String pid=db.getid(email,passward);
                    db.addAmount(pid,amount,"0");
                    String newamt= db.getAmount(email,passward);
                    int amount1=Integer.parseInt(amount)+Integer.parseInt(newamt);
                    String namt=Integer.toString(amount1);
                    try{
                        boolean deposit=db.updateAmt(namt,email,passward);
                        if(deposit){
                            Toast.makeText(DepositByAdmin.this, "Amount deposited", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(DepositByAdmin.this, "Amount not deposited", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch(Exception ex){
                        Toast.makeText(DepositByAdmin.this, ""+ex, Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(DepositByAdmin.this, "Incorrect Passward", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}