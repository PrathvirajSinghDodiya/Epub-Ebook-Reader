package com.example.epubebookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<viewHolder> {
    private Context context;
    private ArrayList<File> pdffiles;
    public Adapter(Context context, ArrayList<File> pdffiles) {
        this.context = context;
        this.pdffiles = pdffiles;
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Toast.makeText(context,"mes13", Toast.LENGTH_SHORT).show();
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.recycleview_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
      holder.filename.setText(pdffiles.get(position).getName());
        Toast.makeText(context, "see:.."+position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemCount() {
        return pdffiles.size();
    }
}

