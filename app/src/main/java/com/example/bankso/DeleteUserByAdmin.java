package com.example.bankso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteUserByAdmin extends AppCompatActivity {
    EditText name,accountnum;
    TextView deleteuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user_by_admin);
        name=findViewById(R.id.name);
        accountnum=findViewById(R.id.accountnumber);
        deleteuser=findViewById(R.id.deleteuserbtn);
        dbManage db=new dbManage(this);
        deleteuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1=name.getText().toString();
                String email=db.getemail(name1);
                int ans=db.deleteUser(email);
                if(ans==1){
                    Toast.makeText(DeleteUserByAdmin.this, name1+" is deleted successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DeleteUserByAdmin.this, name1+" is not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}