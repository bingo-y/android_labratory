package com.bingo.laboratory.ui.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

import com.bingo.laboratory.support.PixelKit;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/11 0011
 * @Description :
 * @VersionName :
 */
public class PaintView extends View{

    Paint paint;

    public PaintView(Context context) {
        super(context);

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3f);
        paint.setTextSize(PixelKit.dp2px(context, 16));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
        弧线(arcs)、填充颜色(argb和color)、 Bitmap、圆(circle和oval)、点(point)、线(line)、矩形(Rect)、图片(Picture)、圆角矩形 (RoundRect)、文本(text)、顶点(Vertices)、路径(path)
         */
        //画圆
//        canvas.drawCircle(100f, 100f, 80f, paint);
        //画线
//        canvas.drawLine(0, 150, 100, 250, paint);
        //画文本
//        canvas.drawText("Android", 0, 300, paint);
        //画长方形
//        RectF rectF = new RectF(0, 0, 120, 120);
//        canvas.drawRect(rectF, paint);
//        canvas.drawRect(50, 50, 450, 450, paint);
        //绘制弧线
//        RectF rectF = new RectF(0, 0, 300, 300);
        //空心
//        canvas.drawArc(rectF,
//                0,
//                90,
//                false,
//                paint);
        //实心
//        canvas.drawArc(rectF,//弧线所使用的矩形区域大小
//                90,//开始角度
//                270,//扫过的角度
//                true,//是否使用中心
//                paint);
        //填颜色
//        canvas.drawColor(Color.RED);
        //画椭圆
//        RectF rectF = new RectF(10, 10, 100, 300);
//        canvas.drawOval(rectF, paint);
        //路径
//        Path path = new Path();
//        path.moveTo(50, 50);
//        path.lineTo(100, 100);
//        path.lineTo(500, 100);
//        path.lineTo(50, 50);
//        canvas.drawPath(path, paint);

        //路径文字
//        String text = "java & android";
//        canvas.drawTextOnPath(text, path, 0, 0, paint);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        canvas.translate(canvas.getWidth()/2, canvas.getHeight()/2); //将位置移动画纸的中间坐标点
        canvas.drawCircle(0, 0, 300, paint); //画圆圈

        //使用path绘制路径文字
        canvas.save();
        canvas.translate(-300, -300);
        Path path = new Path();
        path.addArc(new RectF(0, 0, 600, 600), 225, 180);
        Paint citePaint = new Paint(paint);
        citePaint.setTextSize(PixelKit.dp2px(getContext(), 14));
        citePaint.setStrokeWidth(1);
        canvas.drawTextOnPath("http://www.android.com", path, 0, 50, citePaint);
        canvas.restore();

        //画刻度
        Paint scaleP = new Paint();
        scaleP.setStrokeWidth(2);
        scaleP.setTextSize(PixelKit.dp2px(getContext(), 14));
        scaleP.setAntiAlias(true);
        scaleP.setStyle(Paint.Style.FILL);
        scaleP.setColor(Color.RED);

        canvas.save();
        canvas.rotate(-150);
        int y = 300;
        int count = 60;
        for (int i = 0; i < count; i++) {
            if (i % 5 == 0) {
                //每五分钟一个长刻度
                canvas.drawLine(0, y, 0, y + 50, scaleP);
                canvas.drawText(""+(i/5 + 1), -4f, y + 80, scaleP);
            } else {
                canvas.drawLine(0, y, 0, y + 25, scaleP);
            }
            //旋转
            canvas.rotate(360/count, 0f, 0f);
        }
        canvas.restore();

        //画指针
        scaleP.setColor(Color.GRAY);
        canvas.drawCircle(0f, 0f, 20, scaleP);
        scaleP.setColor(Color.RED);
        canvas.drawCircle(0f, 0f, 10, scaleP);
        scaleP.setStrokeWidth(4);
        canvas.drawLine(0f, 60f, 0f, -200f, scaleP);

    }

    /**
     * Paint类介绍

     *

     * Paint即画笔，在绘图过程中起到了极其重要的作用，画笔主要保存了颜色，

     * 样式等绘制信息，指定了如何绘制文本和图形，画笔对象有很多设置方法，

     * 大体上可以分为两类，一类与图形绘制相关，一类与文本绘制相关。

     *

     * 1.图形绘制

     * setARGB(int a,int r,int g,int b);

     * 设置绘制的颜色，a代表透明度，r，g，b代表颜色值。

     *

     * setAlpha(int a);

     * 设置绘制图形的透明度。

     *

     * setColor(int color);

     * 设置绘制的颜色，使用颜色值来表示，该颜色值包括透明度和RGB颜色。

     *

     * setAntiAlias(boolean aa);

     * 设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。

     *

     * setDither(boolean dither);

     * 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰

     *

     * setFilterBitmap(boolean filter);

     * 如果该项设置为true，则图像在动画进行中会滤掉对Bitmap图像的优化操作，加快显示

     * 速度，本设置项依赖于dither和xfermode的设置

     *

     * setMaskFilter(MaskFilter maskfilter);

     * 设置MaskFilter，可以用不同的MaskFilter实现滤镜的效果，如滤化，立体等       *

     * setColorFilter(ColorFilter colorfilter);

     * 设置颜色过滤器，可以在绘制颜色时实现不用颜色的变换效果

     *

     * setPathEffect(PathEffect effect);

     * 设置绘制路径的效果，如点画线等

     *

     * setShader(Shader shader);

     * 设置图像效果，使用Shader可以绘制出各种渐变效果

     *

     * setShadowLayer(float radius ,float dx,float dy,int color);

     * 在图形下面设置阴影层，产生阴影效果，radius为阴影的角度，dx和dy为阴影在x轴和y轴上的距离，color为阴影的颜色

     *

     * setStyle(Paint.Style style);

     * 设置画笔的样式，为FILL，FILL_OR_STROKE，或STROKE

     *

     * setStrokeCap(Paint.Cap cap);

     * 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样式

     * Cap.ROUND,或方形样式Cap.SQUARE

     *

     * setStrokeJoin(Paint.Join join);

     * 设置绘制时各图形的结合方式，如平滑效果等

     *

     * setStrokeWidth(float width);

     * 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的粗细度

     *

     * setXfermode(Xfermode xfermode);

     * 设置图形重叠时的处理方式，如合并，取交集或并集，经常用来制作橡皮的擦除效果

     *

     * 2.文本绘制

     * setFakeBoldText(boolean fakeBoldText);

     * 模拟实现粗体文字，设置在小字体上效果会非常差

     *

     * setSubpixelText(boolean subpixelText);

     * 设置该项为true，将有助于文本在LCD屏幕上的显示效果

     *

     * setTextAlign(Paint.Align align);

     * 设置绘制文字的对齐方向

     *

     * setTextScaleX(float scaleX);

     * 设置绘制文字x轴的缩放比例，可以实现文字的拉伸的效果

     *

     * setTextSize(float textSize);

     * 设置绘制文字的字号大小

     *

     * setTextSkewX(float skewX);

     * 设置斜体文字，skewX为倾斜弧度

     *

     * setTypeface(Typeface typeface);

     * 设置Typeface对象，即字体风格，包括粗体，斜体以及衬线体，非衬线体等

     *

     * setUnderlineText(boolean underlineText);

     * 设置带有下划线的文字效果

     *

     * setStrikeThruText(boolean strikeThruText);

     * 设置带有删除线的效果

     */
}
