package com.example.epubebookapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;


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
        try {
            InputStream inputStream = new FileInputStream(pdffiles.get(position).getAbsolutePath());
            Book book = (new EpubReader()).readEpub(inputStream);
            Bitmap coverImage = BitmapFactory.decodeStream(book.getCoverImage()
                    .getInputStream());
            holder.image.setImageBitmap(coverImage);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
        ImageView image;
        TextView filename;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            filename = itemView.findViewById(R.id.txtname);
            image = itemView.findViewById(R.id.image);
        }
    }
}

