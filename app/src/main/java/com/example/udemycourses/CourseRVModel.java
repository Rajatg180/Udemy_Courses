package com.example.udemycourses;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseRVModel implements Parcelable {
    private String courseName;
    private String coursePrice;
    private String courseDescription;
    private String courseLink;
    private String courseImage;
    private String courseSuitedFor;
    private String courseID;

    public CourseRVModel(){

    }

    public CourseRVModel (String courseName, String coursePrice, String courseDescription, String courseLink, String courseImage, String courseSuitedFor, String courseID) {
        this.courseName = courseName;
        this.coursePrice = coursePrice;
        this.courseDescription = courseDescription;
        this.courseLink = courseLink;
        this.courseImage = courseImage;
        this.courseSuitedFor = courseSuitedFor;
        this.courseID = courseID;

    }

    protected CourseRVModel(Parcel in) {
        courseName = in.readString();
        coursePrice = in.readString();
        courseDescription = in.readString();
        courseLink = in.readString();
        courseImage = in.readString();
        courseSuitedFor = in.readString();
        courseID = in.readString();
    }

    public static final Creator<CourseRVModel> CREATOR = new Creator<CourseRVModel>() {
        @Override
        public CourseRVModel createFromParcel(Parcel in) {
            return new CourseRVModel(in);
        }

        @Override
        public CourseRVModel[] newArray(int size) {
            return new CourseRVModel[size];
        }
    };

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public String getCourseSuitedFor() {
        return courseSuitedFor;
    }

    public void setCourseSuitedFor(String courseSuitedFor) {
        this.courseSuitedFor = courseSuitedFor;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseName);
        dest.writeString(coursePrice);
        dest.writeString(courseDescription);
        dest.writeString(courseLink);
        dest.writeString(courseImage);
        dest.writeString(courseSuitedFor);
        dest.writeString(courseID);
    }
}
