package com.bingo.laboratory.ui.matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.widget.ImageView;

import com.bingo.laboratory.R;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/10 0010
 * @Description :
 * @VersionName :
 */
public class MatrixView extends ImageView {

    private Matrix matrix;
    private Bitmap bitmap;

    public MatrixView(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.cheery_girl);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画出原图像
        canvas.drawBitmap(bitmap, 0, 0, null);
        //画出转换后图像
        canvas.drawBitmap(bitmap, matrix, null);
    }

    @Override
    public void setImageMatrix(Matrix matrix) {
        super.setImageMatrix(matrix);
        this.matrix.set(matrix);
    }

    public Bitmap getImageBitmap() {
        return bitmap;
    }
}
