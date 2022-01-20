package com.example.epubebookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
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

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        runtimepermission();






    }
    // runtime permission function by hemant vishwakarma
      private void runtimepermission()
    {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response)
                    {
                        ArrayList<File> Filename = findpdf(Environment.getExternalStorageDirectory());

                        binding.RecycleView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3,GridLayoutManager.VERTICAL,false));
                        adapter t = new adapter(getApplicationContext(),Filename);
                        //set adapter in recycler view
                        binding.RecycleView.setAdapter(t);
                     }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response)
                    {
                        finish();

                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token)
                    {
                     token.continuePermissionRequest();
                    }
                }).check();
    }
      // pdf reader function by hemant vishwakarma
     public ArrayList<File> findpdf(File file) {
         ArrayList<File> arrayList = new ArrayList<>();
         File[] files = file.listFiles();
         if(files!=null){
         for (File singlefile : files) {
             if (singlefile.isDirectory() && !singlefile.isHidden()) {
                 arrayList.addAll(findpdf(singlefile));
             } else {
                 if (singlefile.getName().endsWith(".pdf") && !singlefile.getName().startsWith(".")) {
                     arrayList.add(singlefile);
                 }
             }
         }
     }
        return  arrayList;
    }




}
