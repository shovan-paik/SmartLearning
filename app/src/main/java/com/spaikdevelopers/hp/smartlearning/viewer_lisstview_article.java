package com.spaikdevelopers.hp.smartlearning;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewer_lisstview_article extends AppCompatActivity {

    DatabaseReference databaseDiscussionUpload;
    ListView listViewDiscussionUpload;
    List<class_discussionUpload> classDiscussionUploadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer_lisstview_article);
        this.setTitle("Article viewer");
        databaseDiscussionUpload = FirebaseDatabase.getInstance().getReference("Discussion");

        listViewDiscussionUpload = findViewById(R.id.listview_article_viewer_ID);
        classDiscussionUploadList = new ArrayList<>();


        //two methods for back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    //override method for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    //Read the Article(listView)
    @Override
    protected void onStart() {
        super.onStart();
        databaseDiscussionUpload.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                classDiscussionUploadList.clear();
                for (DataSnapshot discussionSnapshot : dataSnapshot.getChildren()) {
                    class_discussionUpload classDiscussionUpload = discussionSnapshot.getValue(class_discussionUpload.class);
                    classDiscussionUploadList.add(classDiscussionUpload);
                }

                class_discussionUploadList adapter = new class_discussionUploadList(viewer_lisstview_article.this, classDiscussionUploadList);
                listViewDiscussionUpload.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
