package com.spaikdevelopers.hp.smartlearning;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class registerDemo2 extends AppCompatActivity {
    private static final String TAG = registerDemo2.class.getSimpleName();

    FirebaseAuth firebaseAuth;

    EditText etname,etemail,etpassword,etphone,et_repeat_password;
    Button btregister, btregister2;


    
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_demo2);

        getSupportActionBar().hide();

        Log.d(TAG,"Debag section");
        Log.i(TAG,"Information section");
        Log.e(TAG,"Error Section");
        Log.w(TAG,"Warning section");
        Log.v(TAG,"Verbose section");

        setupUIViews();
        firebaseAuth = FirebaseAuth.getInstance();

        btregister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registerDemo2.this,loginDemo2.class));
            }
        });

        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userRegistration();
            }
        });
    }

    private void setupUIViews(){
        etname = findViewById(R.id.etname);
        etemail = findViewById(R.id.etemail);
        etphone = findViewById(R.id.etphone);
        etpassword = findViewById(R.id.etpassword);
        et_repeat_password = findViewById(R.id.et_repeat_password);



        btregister = findViewById(R.id.btregister);
        btregister2 = findViewById(R.id.btregister2);


        progressDialog = new ProgressDialog(this);




    }

    private void userRegistration(){


        String name = etname.getText().toString().trim();
        String email = etemail.getText().toString().trim();
        String phone = etphone.getText().toString().trim();
        String password = etpassword.getText().toString().trim();
        String repeat_password = et_repeat_password.getText().toString().trim();




        if(name.isEmpty()){
            etname.setError("Enter your Name.");
            etname.requestFocus();
            return;
        }
        if(email.isEmpty())
        {
           etemail.setError("Enter your Email Address.");
           etemail.requestFocus();
           return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etemail.setError("Enter a valid email address.");
            etemail.requestFocus();
            return;
        }
        if(phone.isEmpty())
        {
            etphone.setError("Enter your Phone Number.");
            etphone.requestFocus();
            return;
        }
        if (password.isEmpty())
        {
            etpassword.setError("Enter a Password.");
            etpassword.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            etpassword.setError("minimum length of password should be 6.");
            etpassword.requestFocus();
            return;
        }

        if (repeat_password.isEmpty())
        {
           et_repeat_password.setError("Repeat your Password.");
            et_repeat_password.requestFocus();
            return;
        }

        if(repeat_password.length()<6)
        {
            et_repeat_password.setError("minimum length of repeat password should be 6.");
            et_repeat_password.requestFocus();
            return;
        }

        if(password.equals(repeat_password))
        {
            //under if start
            progressDialog.setMessage("trying to registration.");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        Toast.makeText(registerDemo2.this,"Registration Successful.",Toast.LENGTH_LONG).show();
                        sendEmailVerification();
                    }
                    else{

                        if(task.getException() instanceof FirebaseAuthUserCollisionException)
                        {
                            Toast.makeText(registerDemo2.this,"User is already register.",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(registerDemo2.this,"Error: "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                }
            });
            //under if end


        }else {

            et_repeat_password.setError("password doesn't match.");
            et_repeat_password.requestFocus();
            return;

        }





    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(registerDemo2.this,"Verification mail was sent.\nPlease verify your email",Toast.LENGTH_LONG).show();
                       firebaseAuth.signOut();
                       finish();
                       startActivity(new Intent(registerDemo2.this,loginDemo2.class));
                   }else {
                       Toast.makeText(registerDemo2.this,"Verification mail wasn't sent.",Toast.LENGTH_LONG).show();
                   }
                }
            });
        }
    }
}
