package com.example.epubebookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class adapter extends RecyclerView.Adapter<viewHolder> {
    private Context context;

    public adapter(Context context, List<File> pdffiles) {
        this.context = context;
        this.pdffiles = pdffiles;
    }

    private List<File> pdffiles;

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new viewHolder(LayoutInflater.from(context).inflate(R.layout./*item layout */,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
      holder.filename.setText(pdffiles.get(position).getName());
      holder.filename.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return pdffiles.size();
    }
}
