package com.myjre.pdfviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    PDFView pdfView;
    Button select;
    int pickpdf;
    private static final int PICKFILE_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pdfView=findViewById(R.id.pdfview);
        select=findViewById(R.id.select);
        pdfView.fromAsset("monureg.pdf").load();
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);

                intent.setType("application/pdf");
                startActivityForResult(intent,PICKFILE_RESULT_CODE);

            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if(resultCode==RESULT_OK){
                    String FilePath = data.getData().getPath();
                    Uri file=Uri.parse(FilePath);
                    Toast.makeText(this,FilePath,Toast.LENGTH_SHORT).show();
                    pdfView.fromUri(file).load();

                    pdfView.setNightMode(true);
                }
                else
                {
                    Toast.makeText(this,"NO Selected",Toast.LENGTH_SHORT).show();
                }

    }
}