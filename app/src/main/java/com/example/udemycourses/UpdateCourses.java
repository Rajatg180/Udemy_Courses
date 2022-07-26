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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class UpdateCourses extends AppCompatActivity {
    private TextInputEditText courseNameEdt,coursePriceEdt,courseSuitedForEdt,courseImgLinkEdt,courseLinkEdt,courseDesEdt;
    private Button btnUpdateCourse,btnDeleteCourse;
    private ProgressBar progressBar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String courseId;
    private CourseRVModel courseRVModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_courses);
        courseNameEdt=findViewById(R.id.idEdtCourseName);
        coursePriceEdt=findViewById(R.id.idEdtCoursePrice);
        courseImgLinkEdt=findViewById(R.id.idEdtCourseImageLink);
        courseSuitedForEdt=findViewById(R.id.idEdtCourseSuitedFor);
        courseLinkEdt=findViewById(R.id.idEdtCourseLink);
        courseDesEdt=findViewById(R.id.idEdtCourseDesc);
        btnUpdateCourse=findViewById(R.id.idBtnAddCourse);
        btnDeleteCourse=findViewById(R.id.idBtnDeleteCourse);
        progressBar=findViewById(R.id.idProgressBar);

        firebaseDatabase=FirebaseDatabase.getInstance();
        courseRVModel=getIntent().getParcelableExtra("courses");
        if(courseRVModel!=null){
            courseNameEdt.setText(courseRVModel.getCourseName());
            coursePriceEdt.setText(courseRVModel.getCoursePrice());
            courseImgLinkEdt.setText(courseRVModel.getCourseImage());
            courseSuitedForEdt.setText(courseRVModel.getCourseSuitedFor());
            courseLinkEdt.setText(courseRVModel.getCourseLink());
            courseDesEdt.setText(courseRVModel.getCourseDescription());
            courseId =courseRVModel.getCourseID();
        }

        databaseReference=firebaseDatabase.getReference("Courses").child(courseId);
        btnUpdateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              progressBar.setVisibility(View.VISIBLE);
                String courseName=courseNameEdt.getText().toString();
                String coursePrice=coursePriceEdt.getText().toString();
                String courseImgLin=courseImgLinkEdt.getText().toString();
                String courseLink=courseLinkEdt.getText().toString();
                String courseSuitedFor=courseSuitedForEdt.getText().toString();
                String courseDesc=courseDesEdt.getText().toString();
                //Putting data in database suing hash map
                Map<String,Object> map=new HashMap<>();
                map.put("courseName",courseName);
                map.put("coursePrice",coursePrice);
                map.put("courseDescription",courseDesc);
                map.put("courseLink",courseLink);
                Object courseImages = new Object();
                map.put("courseImage",courseImages);
                Object suitedFor = new Object();
                map.put("courseSuitedFor",suitedFor);
                Object courseID = new Object();
                map.put("courseID",courseID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        progressBar.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(UpdateCourses.this, "Course Updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UpdateCourses.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(UpdateCourses.this, "Failed To Update Course..", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        btnDeleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              deleteCourse();
            }
        });

    }
    private void deleteCourse(){
        databaseReference.removeValue();
        Toast.makeText(this, "Course Deleted..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UpdateCourses.this,MainActivity.class));
    }
}