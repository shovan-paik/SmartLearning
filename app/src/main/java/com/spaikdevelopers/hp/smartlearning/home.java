package com.spaikdevelopers.hp.smartlearning;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    private CardView cardview_courseId, cardview_ArticalId,
            cardview_admin_panel_Id, cardview_feedbackId,
            cardview_aboutusId, cardview_logoutId;


    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.setTitle("Home");

        firebaseAuth = FirebaseAuth.getInstance();

        cardview_courseId = findViewById(R.id.cardview_courseId);
        cardview_ArticalId = findViewById(R.id.cardview_ArticalId);
        cardview_admin_panel_Id = findViewById(R.id.cardview_admin_panel_Id);
        cardview_feedbackId = findViewById(R.id.cardview_feedbackId);
        cardview_aboutusId = findViewById(R.id.cardview_aboutusId);
        cardview_logoutId = findViewById(R.id.cardview_logoutId);


        cardview_courseId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this, courses_recycleview.class));
            }
        });

        cardview_ArticalId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this, viewer_lisstview_article.class));
            }
        });

        cardview_admin_panel_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this, adminPanel_login_activity.class));
            }
        });

        cardview_feedbackId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this, feedback.class));
            }
        });

        cardview_logoutId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogOut();
            }
        });


        cardview_aboutusId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,about_us.class));
            }
        });


    }


    //For Menu item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu_layout,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_shareID){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plane");

            String url = "https://www.facebook.com/paik.shovan";
            String subject = "Smart Learning App";
            String body = "This app is very useful to learn " +
                    "Computer Science and Engineering courses." +
                    " The App Link is: "+url;

            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);

            startActivity(Intent.createChooser(intent,"Share with: "));
        }
        return super.onOptionsItemSelected(item);
    }

    //Exit App
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert")
                .setMessage("Do you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        home.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    public void LogOut() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(home.this, loginDemo2.class));
    }


}
