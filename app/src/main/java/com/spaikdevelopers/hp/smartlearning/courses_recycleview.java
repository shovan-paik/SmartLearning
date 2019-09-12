package com.spaikdevelopers.hp.smartlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;


import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class courses_recycleview extends AppCompatActivity {
    private SearchView searchView;
    private FirebaseAuth firebaseAuth;

    RecyclerView recyclerView;
    CourseAdapter adapter;
    List<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_recycleview);

        this.setTitle("Courses");

        firebaseAuth = FirebaseAuth.getInstance();
        searchView = findViewById(R.id.searchviewID);


        courseList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adding some items to our list
        courseList.add(
                new Course(
                        1,
                        "Java",
                        R.drawable.java));

        courseList.add(
                new Course(
                        1,
                        "Database",
                        R.drawable.database));

        courseList.add(
                new Course(
                        1,
                        "C Programming",
                        R.drawable.cprogramming));

        courseList.add(
                new Course(
                        1,
                        "Compiler Design",
                        R.drawable.compilerdesign));

        courseList.add(
                new Course(
                        1,
                        "Software Engineering",
                        R.drawable.softwareengineering));

        courseList.add(
                new Course(
                        1,
                        "Data Communication",
                        R.drawable.datacommunication));


        adapter = new CourseAdapter(this, courseList);
        recyclerView.setAdapter(adapter);


        //searchview
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String userInput = newText.toLowerCase();
                List<Course> newList = new ArrayList<>();

                for (Course name : courseList){
                    if(name.getTitle().toLowerCase().contains(userInput)){
                        newList.add(name);
                    }
                }

                adapter.updatelist(newList);
                return true;
            }
        });//search view end




        //two methods for back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    //logout method
    public void LogOut(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(courses_recycleview.this,loginDemo2.class));
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
            startActivity(new Intent(courses_recycleview.this,home.class));
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
