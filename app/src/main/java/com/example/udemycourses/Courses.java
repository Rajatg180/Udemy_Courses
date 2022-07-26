package com.example.udemycourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;

public class Courses extends AppCompatActivity {
    private TextInputEditText courseNameEdt,coursePriceEdt,courseSuitedForEdt,courseImgLinkEdt,courseLinkEdt,courseDesEdt;
    private Button btnAddCourse;
    private ProgressBar progressBar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String courseId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        courseNameEdt=findViewById(R.id.idEdtCourseName);
        coursePriceEdt=findViewById(R.id.idEdtCoursePrice);
        courseImgLinkEdt=findViewById(R.id.idEdtCourseImageLink);
        courseSuitedForEdt=findViewById(R.id.idEdtCourseSuitedFor);
        courseLinkEdt=findViewById(R.id.idEdtCourseLink);
        courseDesEdt=findViewById(R.id.idEdtCourseDesc);
        btnAddCourse=findViewById(R.id.btnAddCourse);
        progressBar=findViewById(R.id.idProgressBar);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Courses");

        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String courseName=courseNameEdt.getText().toString();
                String coursePrice=coursePriceEdt.getText().toString();
                String courseImgLin=courseImgLinkEdt.getText().toString();
                String courseLink=courseLinkEdt.getText().toString();
                String courseSuitedFor=courseSuitedForEdt.getText().toString();
                String courseDesc=courseDesEdt.getText().toString();
                courseId = courseName;
                CourseRVModel courseRVModel=new CourseRVModel(courseName,coursePrice,courseDesc,courseLink,courseImgLin,courseSuitedFor,courseId);
                //adding this model to database
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        progressBar.setVisibility(View.GONE);
                        databaseReference.child(courseId).setValue(courseRVModel);
                        Toast.makeText(Courses.this, "Course Added...", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(Courses.this,MainActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Courses.this, "Error is"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

    }
}