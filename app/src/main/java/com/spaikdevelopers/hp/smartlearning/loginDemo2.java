package com.spaikdevelopers.hp.smartlearning;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.gms.tasks.OnFailureListener;

public class loginDemo2 extends AppCompatActivity {
    private static final String TAG = loginDemo2.class.getSimpleName();

    private EditText etemail, etpassword;
    private Button btlogin, btregisterhare;


    private FirebaseAuth firebaseAuth;


    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_demo2);

        getSupportActionBar().hide();

        Log.d(TAG, "Debag section");
        Log.i(TAG, "Information section");
        Log.e(TAG, "Error Section");
        Log.w(TAG, "Warning section");
        Log.v(TAG, "Verbose section");

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        progressDialog = new ProgressDialog(this);

        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        btlogin = findViewById(R.id.btlogin);
        btregisterhare = findViewById(R.id.btregisterhare);

        if (user != null) {
            finish();
            startActivity(new Intent(loginDemo2.this, home.class));
        }


        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etemail.getText().toString();
                String password = etpassword.getText().toString();


                if (email.isEmpty()) {
                    etemail.setError("Enter your Email Address.");
                    etemail.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etemail.setError("Enter your valid email address.");
                    etemail.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    etpassword.setError("Enter your Password.");
                    etpassword.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    etpassword.setError("minimum length of password should be 6.");
                    etpassword.requestFocus();
                    return;
                } else {
                    signIn(email, password);
                }
            }
        });

        btregisterhare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginDemo2.this, registerDemo2.class));
            }
        });
    }




    private void signIn(String userEmail, String userPassword) {
        progressDialog.setMessage("logging");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    checkEmailVerification();


                } else {
                    progressDialog.dismiss();
                    Toast.makeText(loginDemo2.this, "Login failed.", Toast.LENGTH_LONG).show();


                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    //notifyUser("Invalid password");
                    //Toast.makeText(loginDemo2.this,"Incorrect password.",Toast.LENGTH_LONG).show();
                    etpassword.setError("Incorrect password.");
                    etpassword.requestFocus();

                } else if (e instanceof FirebaseAuthInvalidUserException) {
                    //notifyUser("Incorrect email address");
                    //Toast.makeText(loginDemo2.this,"Incorrect email address.",Toast.LENGTH_LONG).show();
                    etemail.setError("Incorrect email address.");
                    etemail.requestFocus();

                }

            }
        });
    }


    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        if (emailflag) {
            //Toast.makeText(loginDemo2.this,"Your email is verified.\nNow you can login.",Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(loginDemo2.this, home.class));
            Toast.makeText(loginDemo2.this, "Login Successful.", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(loginDemo2.this, "Please verify your email.", Toast.LENGTH_LONG).show();
            firebaseAuth.signOut();
        }
    }


}
