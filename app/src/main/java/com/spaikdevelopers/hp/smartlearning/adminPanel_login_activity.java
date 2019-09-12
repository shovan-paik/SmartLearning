package com.spaikdevelopers.hp.smartlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class adminPanel_login_activity extends AppCompatActivity {
    EditText etpassword;
    Button btlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel_login_activity);
        this.setTitle("Admin checker");

        etpassword = findViewById(R.id.etpassword);
        btlogin = findViewById(R.id.btlogin);


        //two methods for back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = etpassword.getText().toString().trim();

                if(password.isEmpty()){
                    etpassword.setError("Enter your Secret Code.");
                    etpassword.requestFocus();
                    return;
                }

                if(password.length()==5)
                {
                    if(password.equals("admin")){
                        startActivity(new Intent(adminPanel_login_activity.this,adminPanel_menuList_activity.class));

                    }else{
                        etpassword.setError("Secret Code is incorrect.");
                        etpassword.requestFocus();
                        return;
                    }


                } else {
                    etpassword.setError("length of Secret Code should be 5");
                    etpassword.requestFocus();
                    return;
                }
            }
        });

    }


    //override method for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home ){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
