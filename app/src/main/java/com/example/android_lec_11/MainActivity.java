package com.example.android_lec_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    char[] d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button internal, cache, external;
        internal = findViewById(R.id.btn_internal);
        cache = findViewById(R.id.btn_cache);
        external = findViewById(R.id.External_btn);

        d = new char[20480*1024];
        Arrays.fill(d, 'M');
        String data = new String(d);



        //Here starts external storage button code

        external.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    File dir =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    File myphoto = new File(dir, "myselfie.jpg");
                    FileWriter fw = new FileWriter(myphoto);
                    fw.write(data);
                    fw.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });




        //Here starts cache button code
        cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    File dir = getCacheDir();
                    File f = new File(dir, "mycache.txt");
                    FileWriter fw = new FileWriter(f);
                    fw.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });






        //Here starts internal storage button code
        internal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{

                                File dir = getFilesDir();
                                File internalFile = new File(dir, "internal_file.txt");
                                FileWriter fw = new FileWriter(internalFile);
                                for(int i = 0; i < data.length(); i++){
                                    fw.write(data);
                                    fw.close();
                                }


                            }
                            catch (Exception e){
                                e.printStackTrace();

                            }
                        }
                    });

            }
                catch (Exception e){
                    e.printStackTrace();

                }
            }
        });
    }
}