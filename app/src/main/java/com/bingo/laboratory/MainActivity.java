package com.bingo.laboratory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bingo.laboratory.ui.canvas.CanvasActivity;
import com.bingo.laboratory.ui.matrix.TransformMatrixActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_matrix).setOnClickListener(this);
        findViewById(R.id.tv_canvas).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_matrix : {
                ActivityDispatchUtil.startActivity(MainActivity.this, TransformMatrixActivity.class);
                break;
            }
            case R.id.tv_canvas : {
                ActivityDispatchUtil.startActivity(MainActivity.this, CanvasActivity.class);
                break;
            }
        }
    }
}
