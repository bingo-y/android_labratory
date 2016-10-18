package com.bingo.laboratory.ui.canvas;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
    TaijiView taijiView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        PaintView pv = new PaintView(this);
//        ManualPaintView manualPaintView = new ManualPaintView(this);

//        taijiView = new TaijiView(this);
//        setContentView(taijiView);
//        final Handler handler = new Handler() {
//            private float degrees = 0;
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                taijiView.setRotate(degrees += 5);
//                this.sendEmptyMessageDelayed(0, 80);
//            }
//        };
//
//        handler.sendEmptyMessageDelayed(0, 20);

        SearchView searchView = new SearchView(this);
        setContentView(searchView);
    }
}
