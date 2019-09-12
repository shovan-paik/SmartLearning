package com.spaikdevelopers.hp.smartlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class viewer_pdf extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer_pdf);

        this.setTitle("pdf viewer");


        //two methods for back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        webView = findViewById(R.id.webviewId);
        progressBar = findViewById(R.id.progressBarId);

        progressBar.setVisibility(View.VISIBLE);

       Bundle bundle = getIntent().getExtras();//use bundle for get data.

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);

       if(bundle!=null){
           String pdfLink = bundle.getString("pdfLink");// here is the get data.
           String url = pdfLink;
           String finalURL = "https://drive.google.com/viewerng/viewer?embedded=true&url=" + url;


           webView.setWebChromeClient(new WebChromeClient(){
               @Override
               public void onProgressChanged(WebView view, int newProgress) {
                   super.onProgressChanged(view, newProgress);

                   if(newProgress == 100){
                       //indicates that url loading is completed.
                       progressBar.setVisibility(View.GONE);
                   }
               }
           });
           webView.loadUrl(finalURL);

       }


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
