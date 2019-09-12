package com.spaikdevelopers.hp.smartlearning;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class adminPanel_discussionUpload_activity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    EditText adminPanel_discussionUpload_et_title,adminPanel_discussionUpload_et_discussion;
    Button discussionUpload_button_add;

    DatabaseReference databaseDiscussionUpload;

    ListView listViewDiscussionUpload;
    List<class_discussionUpload> classDiscussionUploadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel_discussion_upload_activity);
        this.setTitle("For Article/Discussion Upload");

        firebaseAuth = FirebaseAuth.getInstance();

        databaseDiscussionUpload = FirebaseDatabase.getInstance().getReference("Discussion");

        adminPanel_discussionUpload_et_title = findViewById(R.id.adminPanel_discussionUpload_et_title);
        adminPanel_discussionUpload_et_discussion = findViewById(R.id.adminPanel_discussionUpload_et_discussion);
        discussionUpload_button_add = findViewById(R.id.discussionUpload_button_add);


        listViewDiscussionUpload = findViewById(R.id.ListView_discussionUpload_ID);


        //two methods for back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        classDiscussionUploadList = new ArrayList<>();
        discussionUpload_button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDiscussion();
            }
        });


        listViewDiscussionUpload.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                class_discussionUpload classDiscussionUpload = classDiscussionUploadList.get(i);
                showUpdateDialog(classDiscussionUpload.getDiscussionID(),classDiscussionUpload.getDiscussionTitle(),classDiscussionUpload.getDiscussionView());

                return false;
            }
        });
    }



    //logout method
    public void LogOut(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(adminPanel_discussionUpload_activity.this,loginDemo2.class));
    }

    //Start Menu Option//back button layout is not here, here use build in layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }



    //override method for back button and right side menu(home+logout)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemid = item.getItemId();
        if(itemid == android.R.id.home ){
            this.finish();
            return true;
        }


        if(itemid == R.id.homeId){
            startActivity(new Intent(adminPanel_discussionUpload_activity.this,home.class));
            return true;
        }

        if(itemid == R.id.logoutId){
            LogOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onStart() {
        super.onStart();
        databaseDiscussionUpload.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                classDiscussionUploadList.clear();
                for (DataSnapshot discussionSnapshot : dataSnapshot.getChildren()){
                    class_discussionUpload classDiscussionUpload = discussionSnapshot.getValue(class_discussionUpload.class);
                    classDiscussionUploadList.add(classDiscussionUpload);
                }

                class_discussionUploadList adapter = new class_discussionUploadList(adminPanel_discussionUpload_activity.this,classDiscussionUploadList);
                listViewDiscussionUpload.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    //For showUpdate dialog - Start
    private  void showUpdateDialog(final String discussionID, String discussionTitle, String discussionView){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.discussion_update_dialogue,null);

        dialogBuilder.setView(dialogView);

        final EditText discussionUpload_et_NewTitleID = dialogView.findViewById(R.id.discussionUpload_et_NewTitleID);
        final EditText discussionUpload_et_NewDiscussionID = dialogView.findViewById(R.id.discussionUpload_et_NewDiscussionID);

        final Button buttonUpdate = dialogView.findViewById(R.id.discussionUpload_button_update);
        final  Button buttonDelete = dialogView.findViewById(R.id.discussionUpload_button_delete);

        dialogBuilder.setTitle("Title: "+"'"+discussionTitle+"'");

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        //for update discussion- Start
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title = discussionUpload_et_NewTitleID.getText().toString().trim();
                String Discussion = discussionUpload_et_NewDiscussionID.getText().toString().trim();
                if(Title.isEmpty())
                {
                    discussionUpload_et_NewTitleID.setError("Enter a new title.");
                    discussionUpload_et_NewTitleID.requestFocus();
                    return;
                }

                if (Discussion.isEmpty())
                {
                    discussionUpload_et_NewDiscussionID.setError("Enter new discussion.");
                    discussionUpload_et_NewDiscussionID.requestFocus();
                    return;
                }

                updateDiscussion(discussionID,Title,Discussion);
                alertDialog.dismiss();
            }
        });//for update discussion- End



        //for button delete listener- Start
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDiscussion(discussionID);
                alertDialog.dismiss();
            }
        });//for button delete listener- End


    }//For showUpdate dialog - End


    //for update discussion method - Start
    private boolean updateDiscussion(String ID, String title, String discussion){
      DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Discussion").child(ID);
      class_discussionUpload classDiscussionUpload = new class_discussionUpload(ID,title,discussion);
      databaseReference.setValue(classDiscussionUpload);
        Toast.makeText(adminPanel_discussionUpload_activity.this,"Update successfully.",Toast.LENGTH_LONG).show();
      return true;
    }//for update discussion method -End



    //for delete discussion method- Start
    private void deleteDiscussion(String discussionID){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Discussion").child(discussionID);
        databaseReference.removeValue();
        Toast.makeText(adminPanel_discussionUpload_activity.this,"Article is deleted",Toast.LENGTH_LONG).show();

    }




    //for add discussion method- start
    private void addDiscussion(){
        String title = adminPanel_discussionUpload_et_title.getText().toString().trim();
        String discussion = adminPanel_discussionUpload_et_discussion.getText().toString().trim();

        if(title.isEmpty())
        {
            adminPanel_discussionUpload_et_title.setError("Enter a title.");
            adminPanel_discussionUpload_et_title.requestFocus();
            return;
        }

        if (discussion.isEmpty())
        {
            adminPanel_discussionUpload_et_discussion.setError("Enter discussion.");
            adminPanel_discussionUpload_et_discussion.requestFocus();
            return;
        }

        String id = databaseDiscussionUpload.push().getKey();
        class_discussionUpload classDiscussionUpload = new class_discussionUpload(id,title,discussion);

        databaseDiscussionUpload.child(id).setValue(classDiscussionUpload);
        Toast.makeText(adminPanel_discussionUpload_activity.this,"Discussion is added.",Toast.LENGTH_LONG).show();
    }//for add discussion method- End





    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemid = item.getItemId();


        if(itemid == R.id.homeId){
            startActivity(new Intent(adminPanel_discussionUpload_activity.this,home.class));
            return true;
        }

        if(itemid == R.id.logoutId){
            LogOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //End Menu Option//*/
}
