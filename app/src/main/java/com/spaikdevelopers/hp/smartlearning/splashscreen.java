package com.spaikdevelopers.hp.smartlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class splashscreen extends AppCompatActivity {
    private ProgressBar prograssbar_p;
    private int progress_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //hiding the Action bar
        getSupportActionBar().hide();



        //hideing Title Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        prograssbar_p = findViewById(R.id.progressbar_id);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWorks();
                startApps();
            }
        });

        thread.start();
    }

    public void startApps(){
        Intent intent = new Intent(splashscreen.this,loginDemo2.class);
        startActivity(intent);
        finish();
    }

    public void doWorks(){

        for(progress_p=21; progress_p<=100; progress_p=progress_p+21){
            try {
                Thread.sleep(1000);
                prograssbar_p.setProgress(progress_p);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
