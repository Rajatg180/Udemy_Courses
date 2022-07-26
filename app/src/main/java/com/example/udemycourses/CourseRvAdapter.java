package com.example.udemycourses;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseRvAdapter extends RecyclerView.Adapter<CourseRvAdapter.ViewHolder>{
    @NonNull
    @Override
    public CourseRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseRvAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
    }
}
