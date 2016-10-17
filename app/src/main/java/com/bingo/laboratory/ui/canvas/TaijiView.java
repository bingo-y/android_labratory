package com.bingo.laboratory.ui.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/17 0017
 * @Description :
 * @VersionName :
 */
public class TaijiView extends View {
    //黑色画笔
    private Paint blackP;
    //白色画笔
    private Paint whiteP;
    private float degree = 0;

    public TaijiView(Context context) {
        super(context);
        init();
    }

    void init() {
        //初始化画笔
        blackP = new Paint();
        blackP.setAntiAlias(true);
        blackP.setColor(Color.BLACK);

        whiteP = new Paint();
        whiteP.setAntiAlias(true);
        whiteP.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();          //画布宽度
        int height = canvas.getHeight();        //画布高度
        //画布移至屏幕中间
        canvas.translate(width / 2, height / 2);
        //设置灰色背景
        canvas.drawColor(Color.GRAY);
        //旋转画布
        canvas.rotate(degree);

        float radius = Math.min(width, height)/2 - 100;
        //绘制区域
        RectF rectF = new RectF(-radius, -radius, radius, radius);
        //画黑白半圆
        canvas.drawArc(rectF, 90, 180, true, blackP);
        canvas.drawArc(rectF, -90, 180, true, whiteP);

        //画小圆
        float smallRadius = radius /2;
        canvas.drawCircle(0, -smallRadius, smallRadius, blackP);
        canvas.drawCircle(0, smallRadius, smallRadius, whiteP);

        //画中间点
        canvas.drawCircle(0, -smallRadius, smallRadius/4, whiteP);
        canvas.drawCircle(0, smallRadius, smallRadius/4, blackP);
    }

    public void setRotate(float degree) {
        this.degree = degree;
        invalidate();
    }
}
