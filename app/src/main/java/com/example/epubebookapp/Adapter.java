package com.example.epubebookapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {
    private final Context context;
    private final ArrayList<File> pdffiles;

    public Adapter(Context context, ArrayList<File> pdffiles) {
        this.context = context;
        this.pdffiles = pdffiles;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.recycleview_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.filename.setText(pdffiles.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EpubActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("BOOK", pdffiles.get(position).getAbsolutePath());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pdffiles.size();
    }


    class viewHolder extends RecyclerView.ViewHolder{

        TextView filename;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            filename = itemView.findViewById(R.id.txtname);
        }
    }
}

