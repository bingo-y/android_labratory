package com.bingo.laboratory.ui.bezier;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bingo.laboratory.ui.canvas.ManualPaintView;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/11 0011
 * @Description :
 * @VersionName :
 */
public class BezierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bezier3View bezierView = new Bezier3View(this);
        setContentView(bezierView);
    }
}
