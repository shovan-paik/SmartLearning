package com.spaikdevelopers.hp.smartlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class c_programming extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button bt_pdf,bt_videotutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_programming);
        this.setTitle(R.string.c_programming_activity_title);

        //two methods for back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        firebaseAuth = FirebaseAuth.getInstance();

        bt_pdf = findViewById(R.id.bt_pdf);
        bt_videotutorial = findViewById(R.id.bt_videotutorial);


        bt_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(c_programming.this,PDFlistCprograming.class));
            }
        });



        bt_videotutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c_programming.this,viewer_video.class);
                intent.putExtra("youtube_videoLink","https://www.youtube.com/watch?v=6nOavbvFvbY&list=PLgH5QX0i9K3pCMBZcul1fta6UivHDbXvz");
                startActivity(intent);
            }
        });
    }

    //logout method
    public void LogOut(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(c_programming.this,loginDemo2.class));
    }

    //Start Menu Option//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemid = item.getItemId();

        if(itemid == android.R.id.home ){
            this.finish();
            return true;
        }

        if(itemid == R.id.homeId){
            startActivity(new Intent(c_programming.this,home.class));
            return true;
        }

        if(itemid == R.id.logoutId){
            LogOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //End Menu Option//
}
