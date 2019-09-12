package com.spaikdevelopers.hp.smartlearning;

import android.content.Context;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<Course> courseList;


    public CourseAdapter(Context mCtx, List<Course> courseList) {
        this.mCtx = mCtx;
        this.courseList = courseList;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {
        final Course course = courseList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(course.getTitle());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(course.getImage()));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cousre_position = holder.getAdapterPosition();

                if(course.getTitle().equals("Java")){
                   mCtx.startActivity(new Intent(mCtx,java.class));
                }

                if(course.getTitle().equals("Database")){
                    mCtx.startActivity(new Intent(mCtx,database.class));
                }

                if(course.getTitle().equals("C Programming")){
                    mCtx.startActivity(new Intent(mCtx,c_programming.class));
                }

                if(course.getTitle().equals("Compiler Design")){
                    mCtx.startActivity(new Intent(mCtx,compiler_design.class));
                }

                if(course.getTitle().equals("Software Engineering")){
                    mCtx.startActivity(new Intent(mCtx,software_engineering.class));
                }

                if(course.getTitle().equals("Data Communication")){
                    mCtx.startActivity(new Intent(mCtx,data_communication.class));
                }




            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        ImageView imageView;
        private LinearLayout linearLayout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            imageView = itemView.findViewById(R.id.imageView);

            linearLayout = itemView.findViewById(R.id.leanerlayoutId);
        }

    }

    public void updatelist(List<Course>newList){
        courseList = new ArrayList<>();
        courseList.addAll(newList);
        notifyDataSetChanged();
    }

}