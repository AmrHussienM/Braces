package com.example.eatit.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.eatit.R;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    EditText ufirstname,ulastname,uemail,upassword,uconfirmpassword,uphone;
    Button btnregister;
    TextInputLayout userfirstnamewrapper,userlastnamewrapper,useremailwrapper,userpasswordwrapper,userconfirmpasswordwrapper
            ,userphonewrapper;
    /*private static Context context;
    private ProgressDialog progressDialog;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //context=getBaseContext();

        initialization();



        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                progressDialog=new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage("wait");
                progressDialog.show();*/

            }
        });
    }


    private void initialization()
    {
        ufirstname=findViewById(R.id.userfirstname);
        ulastname=findViewById(R.id.userlastname);
        uemail=findViewById(R.id.useremail);
        upassword=findViewById(R.id.userpassword);
        uphone=findViewById(R.id.usernumber);
        btnregister=findViewById(R.id.userbutton);
        userfirstnamewrapper=findViewById(R.id.userfirstnamewrapper);
        userlastnamewrapper=findViewById(R.id.userlastnamewrapper);
        userpasswordwrapper=findViewById(R.id.userpasswordwrapper);
        useremailwrapper=findViewById(R.id.useremailwrapper);
        userphonewrapper=findViewById(R.id.usernumberwrapper);
    }
}
