package com.bingo.laboratory.refresh.header;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.bingo.laboratory.support.PixelKit;
import com.github.ybq.android.spinkit.style.FadingCircle;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.indicator.PtrTensionIndicator;

public class RentalsSunHeaderView extends View implements PtrUIHandler {

    private FadingCircle mDrawable;
    private PtrFrameLayout mPtrFrameLayout;
    private PtrTensionIndicator mPtrTensionIndicator;

    public RentalsSunHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RentalsSunHeaderView(Context context) {
        super(context);
        init();
    }

    public RentalsSunHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setUp(PtrFrameLayout ptrFrameLayout) {
        mPtrFrameLayout = ptrFrameLayout;
        mPtrTensionIndicator = new PtrTensionIndicator();
        mPtrFrameLayout.setPtrIndicator(mPtrTensionIndicator);
    }

    private void init() {
        mDrawable = new FadingCircle();
        mDrawable.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int height = mDrawable.getTotalDragDistance() * 5 / 4;
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height + getPaddingTop() + getPaddingBottom(), MeasureSpec.EXACTLY);
        int height = mDrawable.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom();
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        mDrawable.setBounds(pl, pt, pl + right - left, pt + bottom - top);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mDrawable.stop();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mDrawable.draw(canvas);
        float percent = mPtrTensionIndicator.getOverDragPercent();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mDrawable.start();
//        float percent = mPtrTensionIndicator.getOverDragPercent();
//        mDrawable.offsetTopAndBottom(mPtrTensionIndicator.getCurrentPosY());
//        mDrawable.setPercent(percent);
        invalidate();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        float percent = mPtrTensionIndicator.getOverDragPercent();
        mDrawable.stop();
//        mDrawable.offsetTopAndBottom(mPtrTensionIndicator.getCurrentPosY());
//        mDrawable.setPercent(percent);
        invalidate();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
//        float percent = mPtrTensionIndicator.getOverDragPercent();
////        mDrawable.offsetTopAndBottom(mPtrTensionIndicator.getCurrentPosY());
////        mDrawable.setPercent(percent);
//        mDrawable.setAlpha((int) (255 * percent));
//        invalidate();

        float percent = Math.min(1f, ptrIndicator.getCurrentPercent());
        if (status == PtrFrameLayout.PTR_STATUS_PREPARE) {
            mDrawable.setAlpha((int) (255 * percent));

            float strokeStart = ((percent) * .8f);
//            mDrawable.setStartEndTrim(0f, Math.min(0.8f, strokeStart));
//            mDrawable.setArrowScale(Math.min(1f, percent));

            // magic
            float rotation = (-0.25f + .4f * percent + percent * 2) * .5f;
//            mDrawable.setProgressRotation(rotation);
            invalidate();
        }
    }

    @Override
    public void invalidateDrawable(Drawable dr) {
        if (dr == mDrawable) {
            invalidate();
        } else {
            super.invalidateDrawable(dr);
        }
    }
}
