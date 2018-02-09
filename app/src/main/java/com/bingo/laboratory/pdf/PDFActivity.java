package com.bingo.laboratory.pdf;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bingo.laboratory.R;
import com.joanzapata.pdfview.PDFView;

/**
 * Created by tuyx on 2017/6/29.
 * Description :
 */

public class PDFActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        pdfView = (PDFView) findViewById(R.id.pdf);
        pdfView.fromAsset("agreement_tripartite_deposit.pdf").load();
    }
}
