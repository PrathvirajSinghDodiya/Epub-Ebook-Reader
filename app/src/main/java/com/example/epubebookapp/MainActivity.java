package com.example.epubebookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // runtime permission function by hemant vishwakarma
      private void runtimepermission()
    {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response)
                    {
                        display();
                     }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response)
                    {

                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token)
                    {
                    token.continuePermissionRequest();
                    }
                }).check();
    }
      // pdf reader function by hemant vishwakarma
     public ArrayList findpdf(File file)
    {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        for(File singlefile:files)
        {
            if(singlefile.isDirectory() && !singlefile.isHidden())
            {
                arrayList.addAll(findpdf(singlefile));
            }
            else
            {
                if(singlefile.getName().endsWith(".pdf"))
                {
                    arrayList.add(singlefile);
                }
            }
        }
        return  arrayList;
    }
    //display pdf files by hemant vishwakarma
    public void display()
    {
        Filename = new ArrayList<>();
        Filename.addAll(findpdf(Environment.getExternalStorageDirectory()));
        adapter = new adapter(this,Filename);
        //set adapter in recycler view
    }
}
