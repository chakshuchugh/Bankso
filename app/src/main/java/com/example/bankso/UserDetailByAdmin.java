package com.example.bankso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserDetailByAdmin extends AppCompatActivity {
    EditText name,accountnum;
    TextView checkdetailbtn,checkdetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_by_admin);
        name=findViewById(R.id.name);
        accountnum=findViewById(R.id.accountnumber);
        checkdetail=findViewById(R.id.checkdetail);
        checkdetailbtn=findViewById(R.id.checkdetailbtn);
        dbManage db=new dbManage(this);
        checkdetailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1=name.getText().toString();
                String acnum=accountnum.getText().toString();
                String email=db.getemail(name1);
                String passward=db.getpassward(name1);
                String accountnum=db.getaccountnumber1(name1);
                if(accountnum.equals(acnum)){
                    String detail1= db.getDetail(email,passward);
                    checkdetail.setText(detail1);
                    checkdetail.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(UserDetailByAdmin.this, "Wrong Account Number", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}