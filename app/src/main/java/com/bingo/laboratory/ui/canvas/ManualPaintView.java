package com.bingo.laboratory.ui.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/12 0012
 * @Description :
 * @VersionName :
 */
public class ManualPaintView extends View {

    Paint paint;
    ArrayList<PointF> graphics = new ArrayList<>();

    public ManualPaintView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        graphics.add(new PointF(event.getX(), event.getY()));
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        for (PointF pointF : graphics) {
            canvas.drawPoint(pointF.x, pointF.y, paint);
        }
    }
}
