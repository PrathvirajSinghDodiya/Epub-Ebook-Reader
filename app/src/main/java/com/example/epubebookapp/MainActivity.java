package com.example.epubebookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epubebookapp.databinding.ActivityMainBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<File> pdflist;
    public TextView textView ;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listV);
        permission();

    }
    public void permission()
    {
        Dexter.withContext(MainActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                pdflist = new ArrayList<>();
                pdflist.addAll(findpdf(Environment.getExternalStorageDirectory()));

                ArrayAdapter<String> Adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,pdflist);
                listView.setAdapter(Adapter);
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }
    public ArrayList<File> findpdf(File file){
        ArrayList allpdf = new ArrayList();
        File[] newFile = file.listFiles();
        if(newFile != null){
            for(File file2: newFile){
                if(!file2.isHidden() && file2.isDirectory()){
                    allpdf.addAll(findpdf(file2));
                }
                else{
                    if(file2.getName().endsWith(".pdf") ){
                        allpdf.add(file2);
                        Toast.makeText(getApplicationContext(), "see:- "+file2.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        return allpdf;
    }



}
