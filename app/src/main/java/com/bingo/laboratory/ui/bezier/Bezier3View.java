package com.bingo.laboratory.ui.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/17 0017
 * @Description :
 * @VersionName :
 */
public class Bezier3View extends View {

    private Paint paint, bPaint;
    private PointF startF, endF, controlF1, controlF2;
    private boolean mode;

    public Bezier3View(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(60);

        startF = new PointF(0, 0);
        endF = new PointF(0, 0);
        controlF1 = new PointF(0, 0);
        controlF2 = new PointF(0, 0);

        bPaint = new Paint();
        bPaint.setColor(Color.RED);
        bPaint.setStrokeWidth(6);
        bPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float centerX = w / 2;
        float centerY = h / 2;

        startF.x = centerX - 300;
        startF.y = centerY;

        controlF1.x = centerX - 100;
        controlF1.y = centerY - 300;

        controlF2.x = centerX + 100;
        controlF2.y = centerY + 300;

        endF.x = centerX + 300;
        endF.y = centerY;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mode) {
            controlF1.x = event.getX();
            controlF1.y = event.getY();
        } else {
            controlF2.x = event.getX();
            controlF2.y = event.getY();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制控制点
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(20);
        canvas.drawPoint(startF.x, startF.y, paint);
        canvas.drawPoint(controlF1.x, controlF1.y, paint);
        canvas.drawPoint(controlF2.x, controlF2.y, paint);
        canvas.drawPoint(endF.x, endF.y, paint);

        //绘制辅助线
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);
        canvas.drawLine(startF.x, startF.y, controlF1.x, controlF1.y, paint);
        canvas.drawLine(controlF1.x, controlF1.y, controlF2.x, controlF2.y, paint);
        canvas.drawLine(controlF2.x, controlF2.y, endF.x, endF.y, paint);

        //绘制贝塞尔曲线
        Path path = new Path();
        path.moveTo(startF.x, startF.y);
        path.cubicTo(controlF1.x, controlF1.y, controlF2.x, controlF2.y, endF.x, endF.y);
        canvas.drawPath(path, bPaint);
    }
}
