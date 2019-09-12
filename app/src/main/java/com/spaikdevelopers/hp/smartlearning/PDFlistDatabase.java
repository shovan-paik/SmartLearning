package com.spaikdevelopers.hp.smartlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

public class PDFlistDatabase extends AppCompatActivity {
    private CardView cardview_fundamental_of_database_1,cardview_fundamental_of_database_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdflist_database);

        this.setTitle(R.string.database_pdf);

        //two methods for back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        cardview_fundamental_of_database_1 = findViewById(R.id.cardview_fundamental_of_database_1);
        cardview_fundamental_of_database_2 = findViewById(R.id.cardview_fundamental_of_database_2);


        cardview_fundamental_of_database_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDFlistDatabase.this,viewer_pdf.class);
                //for send the data.
                intent.putExtra("pdfLink","https://firebasestorage.googleapis.com/v0/b/smartlearning-d59ea.appspot.com/o/Elmasri-Navathe-Fundamentals-of-Database-Systems-5th-Editi.pdf?alt=media&token=857189a3-bc24-4c49-928a-3bd2660019a1");
                startActivity(intent);

            }
        });


        cardview_fundamental_of_database_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDFlistDatabase.this,viewer_pdf.class);
                //for send the data.
                intent.putExtra("pdfLink","https://firebasestorage.googleapis.com/v0/b/smartlearning-d59ea.appspot.com/o/Elmasri-Navathe-Fundamentals-of-Database-Systems-5th-Editi.pdf?alt=media&token=857189a3-bc24-4c49-928a-3bd2660019a1");
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
