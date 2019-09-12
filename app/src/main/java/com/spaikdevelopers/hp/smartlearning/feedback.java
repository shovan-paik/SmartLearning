package com.spaikdevelopers.hp.smartlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class feedback extends AppCompatActivity {
    EditText et_nameID, et_commentID;
    Button button_sendID, button_resetID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        this.setTitle("Feedback");

        et_nameID = findViewById(R.id.et_nameID);
        et_commentID = findViewById(R.id.et_commentID);

        button_sendID = findViewById(R.id.button_sendID);
        button_resetID = findViewById(R.id.button_resetID);



        //two methods for back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        button_sendID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = et_nameID.getText().toString().trim();
                String comment = et_commentID.getText().toString().trim();

                if (name.isEmpty()) {
                    et_nameID.setError("Enter name");
                    et_nameID.requestFocus();
                    return;
                }

                if (comment.isEmpty()) {
                    et_commentID.setError("Enter message or comment or advice.");
                    et_commentID.requestFocus();
                    return;
                }

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/email");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"shovanpaik93@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from Smart Learning App.");
                intent.putExtra(Intent.EXTRA_TEXT, "Name: " + name + "\nMessage: " + comment);
                startActivity(Intent.createChooser(intent, "Feedback with"));

            }
        });

        button_resetID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_nameID.setText("");
                et_commentID.setText("");
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
