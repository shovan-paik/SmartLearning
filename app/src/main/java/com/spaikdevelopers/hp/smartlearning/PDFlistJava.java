package com.spaikdevelopers.hp.smartlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class PDFlistJava extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private CardView cardview_javabook_Herbert_Schildt, cardview_introductionToJavaProgramming,
    cardview_java_programming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdflist_java);

        this.setTitle(R.string.java_pdf);

        //two methods for back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();

        cardview_javabook_Herbert_Schildt = findViewById(R.id.cardview_javabook_Herbert_Schildt);
        cardview_introductionToJavaProgramming = findViewById(R.id.cardview_introductionToJavaProgramming);
        cardview_java_programming = findViewById(R.id.cardview_java_programming);



        cardview_javabook_Herbert_Schildt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDFlistJava.this,viewer_pdf.class);
                //for send the data.
                intent.putExtra("pdfLink","https://firebasestorage.googleapis.com/v0/b/smartlearning-d59ea.appspot.com/o/java-the-complete-reference-7th-edition%20By%20Herbert%20Schildt.pdf?alt=media&token=30900181-13c7-4439-ac34-f8163c85898f");
                startActivity(intent);

            }
        });


        cardview_introductionToJavaProgramming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDFlistJava.this,viewer_pdf.class);
                //for send the data.
                intent.putExtra("pdfLink","https://firebasestorage.googleapis.com/v0/b/smartlearning-d59ea.appspot.com/o/IntroductionToJavaProgramming8thEditionByY_DanilelLiang.pdf?alt=media&token=6701fa3b-9e95-4bfa-9985-353598367fcb");
                startActivity(intent);
            }
        });




        cardview_java_programming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDFlistJava.this,viewer_pdf.class);
                //for send the data.
                intent.putExtra("pdfLink","https://firebasestorage.googleapis.com/v0/b/smartlearning-d59ea.appspot.com/o/JavaProgramming%20FromProblemAnalysisToPgmDesig%204th_ed_D_Malik.pdf?alt=media&token=309d1d92-4c74-4bc2-8936-b9cae557f350");
                startActivity(intent);
            }
        });




    }



    //logout method
    public void LogOut(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(PDFlistJava.this,loginDemo2.class));
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
            startActivity(new Intent(PDFlistJava.this,home.class));
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
