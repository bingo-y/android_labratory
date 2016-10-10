package com.bingo.laboratory.ui.matrix;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/10 0010
 * @Description :
 * @VersionName :
 */
public class TransformMatrixActivity extends AppCompatActivity implements View.OnTouchListener {

    MatrixView matrixView;
    int count = 0;
    int max = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        matrixView = new MatrixView(this);
        matrixView.setScaleType(ImageView.ScaleType.MATRIX);
        matrixView.setOnTouchListener(this);

        setContentView(matrixView);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            Matrix matrix = new Matrix();
            Log.d("TransformMatrixActivity", "image size: width x height = " +  matrixView.getImageBitmap().getWidth() + " x " + matrixView.getImageBitmap().getHeight());
            if (count % max == 0) {
                // 平移
                matrix.postTranslate(matrixView.getImageBitmap().getWidth(), matrixView.getImageBitmap().getHeight());
                // 在x方向平移view.getImageBitmap().getWidth()，在y轴方向view.getImageBitmap().getHeight()
//                matrixView.setImageMatrix(matrix);
            } else if (count % max == 1) {
                //围绕中心旋转
                matrix.postRotate(45f, matrixView.getImageBitmap().getWidth() / 2f, matrixView.getImageBitmap().getHeight() / 2f);
                //平移  防止重叠
                matrix.postTranslate(0f, matrixView.getImageBitmap().getHeight() * 1.5f);
            } else if (count % max == 2) {
                //放大
                matrix.setScale(2f, 2f);
                //平移防止重叠
                matrix.postTranslate(matrixView.getImageBitmap().getWidth(), matrixView.getImageBitmap().getHeight());
            } else if (count % max == 3) {
                //缩小
                matrix.setScale(0.5f, 0.5f);
                //平移防止重叠
                matrix.postTranslate(matrixView.getImageBitmap().getWidth(), matrixView.getImageBitmap().getHeight());
            } else if (count % max == 4) {
                //错切 水平
                matrix.setSkew(2f, 0f);
                //平移防止重叠
                matrix.postTranslate(matrixView.getImageBitmap().getWidth(), 0f);
            } else if (count % max == 5) {
                //错切 垂直
                matrix.setSkew(0f, 0.5f);
                //平移防止重叠
                matrix.postTranslate(0f, matrixView.getImageBitmap().getHeight());
            } else if (count % max == 6) {
                //错切 水平+垂直
                matrix.setSkew(0.5f, 0.5f);
                //平移防止重叠
                matrix.postTranslate(matrixView.getImageBitmap().getWidth(), matrixView.getImageBitmap().getHeight());
            } else if (count % max == 7) {
                //对称 水平
                float matrix_values[] = {1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f};
                matrix.setValues(matrix_values);
                //平移防止重叠
                matrix.postTranslate(0f, matrixView.getImageBitmap().getHeight() * 2f);
            } else if (count % max == 8) {
                //对称 垂直
                float matrix_values[] = {-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
                matrix.setValues(matrix_values);
                //平移防止重叠
                matrix.postTranslate(matrixView.getImageBitmap().getWidth() * 2f, 0f);
            } else if (count % max == 9) {
                //对称 对称线为y=x
                float matrix_values[] = {0f, -1f, 0f, -1f, 0f, 0f, 0f, 0f, 1f};
                matrix.setValues(matrix_values);
                //平移防止重叠
                matrix.postTranslate(matrixView.getImageBitmap().getWidth() + matrixView.getImageBitmap().getHeight(),
                        matrixView.getImageBitmap().getWidth() + matrixView.getImageBitmap().getHeight());
            }
            matrixView.setImageMatrix(matrix);
            count ++;

            // 下面的代码是为了查看matrix中的元素
            float[] matrixValues = new float[9];
            matrix.getValues(matrixValues);
            for(int i = 0; i < 3; ++i) {
                String temp = new String();
                for(int j = 0; j < 3; ++j) {
                    temp += matrixValues[3 * i + j ] + "\t";
                }
                Log.d("TransformMatrixActivity", temp);
            }
            matrixView.invalidate();
        }
        return true;
    }
}
