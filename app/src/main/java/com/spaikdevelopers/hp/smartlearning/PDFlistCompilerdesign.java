package com.spaikdevelopers.hp.smartlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

public class PDFlistCompilerdesign extends AppCompatActivity {
   private CardView cardview_compiler_principle,cardview_principle_of_compiler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdflist_compilerdesign);
        this.setTitle(R.string.compiler_design_pdf);

        //two methods for back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        cardview_compiler_principle = findViewById(R.id.cardview_compiler_principle);
        cardview_principle_of_compiler = findViewById(R.id.cardview_principle_of_compiler);



        cardview_compiler_principle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDFlistCompilerdesign.this,viewer_pdf.class);
                //for send the data.
                intent.putExtra("pdfLink","https://firebasestorage.googleapis.com/v0/b/smartlearning-d59ea.appspot.com/o/Compiler%20Principles%2C%20Techniques%20and%20Tools.pdf?alt=media&token=341731ee-42fa-4e02-91c3-160a3c0332d8");
                startActivity(intent);

            }
        });


        cardview_principle_of_compiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDFlistCompilerdesign.this,viewer_pdf.class);
                //for send the data.
                intent.putExtra("pdfLink","https://firebasestorage.googleapis.com/v0/b/smartlearning-d59ea.appspot.com/o/Principles_of_Compilers__A_New_Approach_to_Compilers_Including_the_Algebraic_Method.pdf?alt=media&token=fc809fcf-fd34-4f8e-beb2-e3f2eed3c47b");
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
