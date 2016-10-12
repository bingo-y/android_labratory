package com.bingo.laboratory.ui.canvas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/11 0011
 * @Description :
 * @VersionName :
 */
public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        PaintView pv = new PaintView(this);
        ManualPaintView manualPaintView = new ManualPaintView(this);
        setContentView(manualPaintView);
    }
}
