package com.example.bankso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddAmount extends AppCompatActivity {
    EditText deposit,passward1;
    TextView amtdeposit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_amount);
        deposit=findViewById(R.id.deposit1);
        passward1=findViewById(R.id.depositPassword);
        amtdeposit=findViewById(R.id.amtdeposit);
        dbManage db=new dbManage(this);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String passward=intent.getStringExtra("passward");

        amtdeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amt=deposit.getText().toString();
                String enteredpass=passward1.getText().toString();
                String userpass=db.getPassward(email,passward);
                if(enteredpass.equals(userpass)){
                    String pid=db.getid(email,passward);
                    db.addAmount(pid,amt+"          ","0");
                    String newamt= db.getAmount(email,passward);
                    int amount=Integer.parseInt(amt)+Integer.parseInt(newamt);
                    String namt=Integer.toString(amount);
                    //Toast.makeText(AddAmount.this, ""+l, Toast.LENGTH_SHORT).show();
                    deposit.setText("");
                    passward1.setText("");
                    try{
                        boolean deposit=db.updateAmt(namt,email,passward);
                        if(deposit){
                            Toast.makeText(AddAmount.this, "Amount deposited", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(AddAmount.this, "Amount not deposited", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch(Exception ex){
                        Toast.makeText(AddAmount.this, ""+ex, Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(AddAmount.this, "Incorrect Passward", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}