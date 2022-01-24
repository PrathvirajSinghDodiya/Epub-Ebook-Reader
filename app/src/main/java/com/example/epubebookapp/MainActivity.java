package com.example.epubebookapp;

import androidx.appcompat.app.AppCompatActivity;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;


import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ArrayList<File> pdflist;

    RecyclerView recyclerView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.listV);

        Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                pdflist = new ArrayList<>();
                pdflist = getfile(Environment.getExternalStorageDirectory());
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false));

                Adapter adapter = new Adapter(getApplicationContext(), pdflist);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();

            }
        }).withErrorListener(new PermissionRequestErrorListener() {
            @Override
            public void onError(DexterError dexterError) {
                Toast.makeText(getApplicationContext(),dexterError.toString(),Toast.LENGTH_LONG).show();
            }
        }).check();







    }



    public ArrayList<File> getfile(File file){
        ArrayList allpdf = new ArrayList();
        File[] newFile = file.listFiles();
        if(newFile != null){
            for(File file2: newFile){
                if(!file2.isHidden() && file2.isDirectory()){
                    allpdf.addAll(getfile(file2));
                }
                else{
                    if(file2.getName().endsWith(".epub") && !file2.getName().startsWith(".")){
                        allpdf.add(file2);
                    }
                }
            }
        }
        return allpdf;
    }
}


