package com.spaikdevelopers.hp.smartlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

public class PDFlistCprograming extends AppCompatActivity {
    private CardView cardview_complete_reference,cardview_expert_c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdflist_cprograming);

        this.setTitle(R.string.c_programming_pdf);

        //two methods for back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        cardview_complete_reference = findViewById(R.id.cardview_complete_reference);
        cardview_expert_c = findViewById(R.id.cardview_expert_c);


        cardview_complete_reference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDFlistCprograming.this,viewer_pdf.class);
                //for send the data.
                intent.putExtra("pdfLink","https://firebasestorage.googleapis.com/v0/b/smartlearning-d59ea.appspot.com/o/C%20The%20Complete%20Reference.pdf?alt=media&token=f9a1930e-4bef-46e5-b1f3-cb92307b2e82");
                startActivity(intent);

            }
        });


        cardview_expert_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDFlistCprograming.this,viewer_pdf.class);
                //for send the data.
                intent.putExtra("pdfLink","https://firebasestorage.googleapis.com/v0/b/smartlearning-d59ea.appspot.com/o/Expert%20C%20Programming.pdf?alt=media&token=fed9bff4-5e65-498f-8076-ab5981f1e6f3");
                startActivity(intent);
            }
        });


    }


    //override method for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemid = item.getItemId();
        if(itemid == android.R.id.home ){
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
