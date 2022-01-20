package com.example.epubebookapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

class viewHolder extends RecyclerView.ViewHolder {

    TextView filename;

    public viewHolder(@NonNull View itemView) {
        super(itemView);

        filename = itemView.findViewById(R.id.txtname);

    }
}
