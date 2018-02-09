package com.bingo.laboratory.refresh.header;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bingo.laboratory.support.PixelKit;
import com.github.ybq.android.spinkit.style.FadingCircle;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.indicator.PtrTensionIndicator;

/**
 * Created by tuyx on 2017/7/8.
 * Description :
 */

public class FadingCircleHeaderView extends FrameLayout implements PtrUIHandler {

    private FadingCircle mDrawable;
//    private PtrFrameLayout mPtrFrameLayout;
    private PtrTensionIndicator mPtrTensionIndicator;
    private ImageView ivContainer;

    public FadingCircleHeaderView(@NonNull Context context) {
        this(context, null);
    }

    public FadingCircleHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FadingCircleHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    public void setUp(PtrFrameLayout ptrFrameLayout) {
//        mPtrFrameLayout = ptrFrameLayout;
//        final PtrUIHandlerHook mPtrUIHandlerHook = new PtrUIHandlerHook() {
//            @Override
//            public void run() {
//
//            }
//        };
//
//        mPtrFrameLayout.setRefreshCompleteHook(mPtrUIHandlerHook);
//    }

    private void init() {
        ivContainer = new ImageView(getContext());
        FrameLayout.LayoutParams lp = new LayoutParams(PixelKit.dp2px(getContext(), 34), PixelKit.dp2px(getContext(), 34));
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        ivContainer.setLayoutParams(lp);
        ivContainer.setBackgroundColor(Color.RED);
        addView(ivContainer);

        mDrawable = new FadingCircle();
        mDrawable.setColor(Color.WHITE);
        mDrawable.onCreateChild();
        ivContainer.setImageDrawable(mDrawable);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mDrawable.stop();
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
//        invalidate();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        mDrawable.stop();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();

        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {

            }
        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {

            }
        }
    }
}
