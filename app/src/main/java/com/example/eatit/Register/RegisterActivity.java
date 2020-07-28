package com.example.eatit.Register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.eatit.HomeActivity;
import com.example.eatit.Model.User;
import com.example.eatit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText ufirstname,ulastname,uemail,upassword,uphone;
    Button btnregister;
    TextInputLayout userfirstnamewrapper,userlastnamewrapper,useremailwrapper,userpasswordwrapper
            ,userphonewrapper;
    private static Context context;
    private ProgressBar progressBarReg;
    private ProgressDialog progressDialog;
    String firstname, lastname, email, password, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context=getBaseContext();
        initialization();


        final RegisterPresenter registerPresenter=new RegisterPresenter(getContext(),progressBarReg);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                progressDialog=new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage("please wait...");
                progressDialog.show();
                registerPresenter.verifyEmailAndPassword(ufirstname,ulastname,uemail,upassword,uphone);
            }
        });
    }

    private static Context getContext()
    {return context;}

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
        progressDialog=new ProgressDialog(getContext());
        progressBarReg=findViewById(R.id.progressbar);
    }
}
