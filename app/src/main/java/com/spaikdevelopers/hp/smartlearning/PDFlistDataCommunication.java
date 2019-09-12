package com.spaikdevelopers.hp.smartlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

public class PDFlistDataCommunication extends AppCompatActivity {
    private CardView cardview_datacommunication,cardview_datacommunication_solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdflist_data_communication);

        this.setTitle(R.string.Data_communication_pdf);

        //two methods for back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        cardview_datacommunication = findViewById(R.id.cardview_datacommunication);
        cardview_datacommunication_solution = findViewById(R.id.cardview_datacommunication_solution);





        cardview_datacommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDFlistDataCommunication.this,viewer_pdf.class);
                //for send the data.
                intent.putExtra("pdfLink","https://firebasestorage.googleapis.com/v0/b/smartlearning-d59ea.appspot.com/o/Data%20Communications%20and%20Networking%204th%20Edition%20by%20theSumit67.pdf?alt=media&token=3ab4a4fe-e5e6-46bb-b9c7-81e534abb9bd");
                startActivity(intent);

            }
        });


        cardview_datacommunication_solution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDFlistDataCommunication.this,viewer_pdf.class);
                //for send the data.
                intent.putExtra("pdfLink","https://firebasestorage.googleapis.com/v0/b/smartlearning-d59ea.appspot.com/o/solutionmanualfordatacommunicationsandnetworkingbybehrouzforouzan5theditioncomplete-140901093822-phpapp01.pdf?alt=media&token=82c46dc0-e3c7-43e1-9013-ffb9963ba074");
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
