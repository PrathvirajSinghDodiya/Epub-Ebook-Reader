package com.example.epubebookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class adapter extends RecyclerView.Adapter<viewHolder> {
    private Context context;
    private List<File> pdffiles;
    public adapter(Context context, List<File> pdffiles) {
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
/*  new pdfadapter

public class PDFAdapter extends ArrayAdapter<File> {
    Context context;
    ViewHolder viewHolder;
    ArrayList<File> al_pdf;

    public PDFAdapter(Context context, ArrayList<File> al_pdf) {
        super(context, R.layout.adapter_pdf, al_pdf);
        this.context = context;
        this.al_pdf = al_pdf;

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (al_pdf.size() > 0) {
            return al_pdf.size();
        } else {
            return 1;
        }
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_pdf, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_filename = (TextView) view.findViewById(R.id.tv_name);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.tv_filename.setText(al_pdf.get(position).getName());
        return view;

    }

    public class ViewHolder {

        TextView tv_filename;
    }

}
*/
