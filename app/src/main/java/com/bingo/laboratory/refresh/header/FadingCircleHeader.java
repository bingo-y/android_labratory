package com.bingo.laboratory.refresh.header;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bingo.laboratory.R;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.SpriteFactory;
import com.github.ybq.android.spinkit.Style;
import com.github.ybq.android.spinkit.sprite.Sprite;

import java.text.SimpleDateFormat;
import java.util.Date;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

public class FadingCircleHeader extends FrameLayout implements PtrUIHandler {


    private int mRotateAniTime = 150;
    private RotateAnimation mFlipAnimation;
    private RotateAnimation mReverseFlipAnimation;

    private RelativeLayout mRouteHeader;
    private TextView mTitleTextView;
    private View mRotateView;
    private SpinKitView mSpinKitView;

    public FadingCircleHeader(Context context) {
        this(context, null);
    }

    public FadingCircleHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FadingCircleHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews(attrs);
    }

    protected void initViews(AttributeSet attrs) {
        buildAnimation();
        View header = LayoutInflater.from(getContext()).inflate(R.layout.view_fading_circle_header, this);

        mRouteHeader = (RelativeLayout) header.findViewById(R.id.ptr_rotate_header);
        mRotateView = header.findViewById(R.id.ptr_rotate_view);

        mTitleTextView = (TextView) header.findViewById(R.id.ptr_rotate_view_header_title);
        mSpinKitView = (SpinKitView) header.findViewById(R.id.spin_kit);
        mSpinKitView.setColor(Color.RED);
        Sprite drawable = SpriteFactory.create(Style.FADING_CIRCLE);
        mSpinKitView.setIndeterminateDrawable(drawable);

        resetView();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void setRotateAniTime(int time) {
        if (time == mRotateAniTime || time == 0) {
            return;
        }
        mRotateAniTime = time;
        buildAnimation();
    }


    private void buildAnimation() {
        mFlipAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mFlipAnimation.setInterpolator(new LinearInterpolator());
        mFlipAnimation.setDuration(mRotateAniTime);
        mFlipAnimation.setFillAfter(true);

        mReverseFlipAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
        mReverseFlipAnimation.setDuration(mRotateAniTime);
        mReverseFlipAnimation.setFillAfter(true);
    }

    private void resetView() {
        hideRotateView();
        mSpinKitView.setVisibility(INVISIBLE);
    }

    private void hideRotateView() {
        mRotateView.clearAnimation();
        mRouteHeader.setVisibility(INVISIBLE);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        resetView();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

        mSpinKitView.setVisibility(INVISIBLE);

        mRouteHeader.setVisibility(VISIBLE);
        if (frame.isPullToRefresh()) {
            mTitleTextView.setText(getResources().getString(in.srain.cube.views.ptr.R.string.cube_ptr_pull_down_to_refresh));
        } else {
            mTitleTextView.setText(getResources().getString(in.srain.cube.views.ptr.R.string.cube_ptr_pull_down));
        }
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        hideRotateView();
        mSpinKitView.setVisibility(VISIBLE);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {

        hideRotateView();
        mSpinKitView.setVisibility(INVISIBLE);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();

        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                crossRotateLineFromBottomUnderTouch(frame);
                if (mRotateView != null) {
                    mRotateView.clearAnimation();
                    mRotateView.startAnimation(mReverseFlipAnimation);
                }
            }
        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                crossRotateLineFromTopUnderTouch(frame);
                if (mRotateView != null) {
                    mRotateView.clearAnimation();
                    mRotateView.startAnimation(mFlipAnimation);
                }
            }
        }
    }

    private void crossRotateLineFromTopUnderTouch(PtrFrameLayout frame) {
        mRouteHeader.setVisibility(VISIBLE);
        if (!frame.isPullToRefresh()) {
            mTitleTextView.setText(in.srain.cube.views.ptr.R.string.cube_ptr_release_to_refresh);
        }
    }

    private void crossRotateLineFromBottomUnderTouch(PtrFrameLayout frame) {
        mRouteHeader.setVisibility(VISIBLE);
        if (frame.isPullToRefresh()) {
            mTitleTextView.setText(getResources().getString(in.srain.cube.views.ptr.R.string.cube_ptr_pull_down_to_refresh));
        } else {
            mTitleTextView.setText(getResources().getString(in.srain.cube.views.ptr.R.string.cube_ptr_pull_down));
        }
    }
}
