package com.bingo.laboratory.ui.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/13 0013
 * @Description :
 * @VersionName :
 */
public class CircleImageView extends ImageView {
    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        //获取src设置的图片
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        if (!(drawable instanceof BitmapDrawable)) {
            return;
        }
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();

        if (b == null) {
            return;
        }
        //复制一个可变的bitmap
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth();
        Bitmap roundBmp = getCroppedBitmap(bitmap, w);
        canvas.drawBitmap(roundBmp, 0, 0, null);
    }

    /**
     * 初始Bitmap对象的缩放裁剪过程
     * @param bmp 初始化对象
     * @param radius 圆直径大小
     * @return 返回一个圆形的缩放裁剪过后的Bitmap对象
     */
    private Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap rbmp;
        //比较初始Bitmap宽高和给定的圆形直径，判断是否需要缩放裁剪Bitmap对象
        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            rbmp = Bitmap.createScaledBitmap(bmp, radius, radius, true);
        } else {
            rbmp = bmp;
        }
        Bitmap output = Bitmap.createBitmap(rbmp.getWidth(), rbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Rect rect = new Rect(0, 0, rbmp.getWidth(), rbmp.getHeight());
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setFilterBitmap(true);
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(rbmp.getWidth() / 2f + 0.7f, rbmp.getHeight() / 2f +0.7f, rbmp.getWidth() / 2f + 0.1f, paint);
        //核心部分，设置两张图片的相交模式，在这里就是上面绘制的Circle和下面绘制的Bitmap
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(rbmp, rect, rect, paint);
        return output;
    }
}
