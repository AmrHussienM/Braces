package com.example.eatit.Register;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.eatit.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPresenter {



    private FirebaseAuth mAuth;
    private DatabaseReference database;
    private ProgressBar progressBar;
    private Context context;

    public RegisterPresenter( Context context, ProgressBar progressBar) {
        database= FirebaseDatabase.getInstance().getReference().child("Users");
        mAuth=FirebaseAuth.getInstance();
        this.progressBar = progressBar;
        this.context = context;
    }

    void verifyEmailAndPassword(EditText firstname, EditText lastname, EditText email, EditText password,EditText phone)
    {
        String sFirstname,sLastname,sEmail,sPassword,sPhone;

        sFirstname=firstname.getText().toString().trim();
        sLastname=lastname.getText().toString().trim();
        sEmail=email.getText().toString().trim();
        sPassword=password.getText().toString().trim();
        sPhone=phone.getText().toString().trim();
        boolean flag=true;

        if(sFirstname.isEmpty()){
            firstname.setError("can't be this field empty");
            flag=false;
        }
        if(sLastname.isEmpty()){
            lastname.setError("can't be this field empty");
            flag=false;
        }
        if(sEmail.isEmpty()){
            email.setError("can't leave this field empty");
            flag=false;
        }
        if(sPassword.isEmpty()){
            password.setError("can't be this field empty");
            flag=false;
        }

        if(sPhone.isEmpty()){
            phone.setError("can't be this field empty");
            flag=false;
        }

        if(flag){
            //TODO loading par
            createNewUser(sFirstname,sLastname,sEmail,sPassword,sPhone,email,password);
        }
    }

    private void createNewUser(final String firstname, final String lastname, final String email, final String password,final String phone,
                               final EditText mTxtEmail, final EditText mTxtPassword)
    {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            String user=mAuth.getCurrentUser().getUid();
                            database.child(user).child("firstname").setValue(firstname);
                            database.child(user).child("lastname").setValue(lastname);
                            database.child(user).child("email").setValue(email);
                            database.child(user).child("password").setValue(password);
                            database.child(user).child("phone").setValue(phone);
                            progressBar.setVisibility(View.GONE);
                            enterToHome();
                        }
                        else
                        {
                            progressBar.setVisibility(View.GONE);
                            String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();

                            switch (errorCode) {

                                case "ERROR_INVALID_CUSTOM_TOKEN":
                                    Toast.makeText(context, "The custom token format is incorrect. Please check the documentation.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_CUSTOM_TOKEN_MISMATCH":
                                    Toast.makeText(context, "The custom token corresponds to a different audience.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_INVALID_CREDENTIAL":
                                    Toast.makeText(context, "The supplied auth credential is malformed or has expired.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_INVALID_EMAIL":
                                    mTxtEmail.setError("The email address is badly formatted.");
                                    mTxtEmail.requestFocus();
                                    break;

                                case "ERROR_WRONG_PASSWORD":
                                    mTxtPassword.setError("password is incorrect ");
                                    mTxtPassword.requestFocus();
                                    mTxtPassword.setText("");
                                    break;

                                case "ERROR_USER_MISMATCH":
                                    Toast.makeText(context, "The supplied credentials do not correspond to the previously signed in user.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_REQUIRES_RECENT_LOGIN":
                                    Toast.makeText(context, "This operation is sensitive and requires recent authentication. Log in again before retrying this request.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                                    Toast.makeText(context, "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_EMAIL_ALREADY_IN_USE":
                                    mTxtEmail.setError("The email address is already in use by another account.");
                                    mTxtEmail.requestFocus();
                                    break;

                                case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                                    Toast.makeText(context, "This credential is already associated with a different user account.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_USER_DISABLED":
                                    Toast.makeText(context, "The user account has been disabled by an administrator.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_USER_TOKEN_EXPIRED":
                                    Toast.makeText(context, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_USER_NOT_FOUND":
                                    Toast.makeText(context, "There is no user record corresponding to this identifier. The user may have been deleted.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_INVALID_USER_TOKEN":
                                    Toast.makeText(context, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_OPERATION_NOT_ALLOWED":
                                    Toast.makeText(context, "This operation is not allowed. You must enable this service in the console.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_WEAK_PASSWORD":
                                    mTxtPassword.setError("The password is invalid it must 6 characters at least");
                                    mTxtPassword.requestFocus();
                                    break;

                            }
                        }
                    }

                });
    }

    private void enterToHome()
    {
        Intent intent=new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }
}
