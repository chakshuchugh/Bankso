package com.example.bankso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Withdrawamount extends AppCompatActivity {
    EditText withdrawamount,pass;
    TextView withdrawbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        withdrawamount=findViewById(R.id.withdrawAmount);
        pass=findViewById(R.id.withdrawPassword);
        withdrawbutton=findViewById(R.id.textView3);
        dbManage db=new dbManage(this);
        Intent intent=getIntent();
        String email=intent.getStringExtra("email");
        String passward=intent.getStringExtra("passward");

        withdrawbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amt=withdrawamount.getText().toString();
                String enteredpass=pass.getText().toString();
                String userpass=db.getPassward(email,passward);
                if(enteredpass.equals(userpass)){
                    String pid=db.getid(email,passward);
                    db.withdrawAmount(pid,"0        ",amt);
                    String newamt= db.getAmount(email,passward);
                    int amount=Integer.parseInt(newamt)-Integer.parseInt(amt);
                    String namt=Integer.toString(amount);
                    //Toast.makeText(AddAmount.this, ""+l, Toast.LENGTH_SHORT).show();
                    withdrawamount.setText("");
                    pass.setText("");
                    try{

                        boolean withdraw=db.updateAmt(namt,email,passward);
                        if(withdraw){
                            Toast.makeText(Withdrawamount.this, "Amount withdrawn", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Withdrawamount.this, "Amount not withdrawn", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch(Exception ex){
                        Toast.makeText(Withdrawamount.this, ""+ex, Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(Withdrawamount.this, "Incorrect Passward", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}