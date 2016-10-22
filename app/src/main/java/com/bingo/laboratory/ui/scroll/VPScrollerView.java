package com.bingo.laboratory.ui.scroll;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/21 0021
 * @Description :
 * @VersionName :
 */
public class VPScrollerView extends ViewGroup {

    //用于完成滚动操作的实例
    Scroller mScroller;

    //判断滚动的最新像素
    int mTouchSlop;

    //按下时的坐标
    float mXDown;

    //当前坐标
    float mXMove;

    //上次触发ACTION_MOVE事件时的屏幕坐标
    float mXLastMove;

    //屏幕左边界
    int leftBorder;

    //屏幕右边界
    int rightBorder;

    public VPScrollerView(Context context) {
        super(context);
        init(context);
    }

    public VPScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VPScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     */
    void init(Context context) {
        mScroller = new Scroller(context);
        // 获取TouchSlop值
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 为ScrollerLayout中的每一个子控件测量大小
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
            }
            leftBorder = getChildAt(0).getLeft();
            rightBorder = getChildAt(childCount - 1).getRight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN : {
                //按下时
                mXDown = ev.getRawX();
                mXLastMove = mXDown;
                Log.d("Inter-down-mXLastMove: ","" + mXLastMove);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                mXMove = ev.getRawX();
                Log.d("Inter-move-mXMove: ","" + mXMove);
                float diff = Math.abs(mXLastMove - mXMove);
                mXLastMove = mXMove;
                Log.d("Inter-move-mXLastMove: ","" + mXLastMove);
                if (diff > mTouchSlop) {
                    //移动距离大于最小移动像素，消耗此次事件
                    return true;
                }
                break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                mXMove = event.getRawX();
                Log.d("touch-move-mXMove: ","" + mXMove);
                int scrollX = (int) (mXLastMove - mXMove);
                Log.d("touch-move-scrollX: ","" + scrollX);
                Log.d("touch-move-getScrollX: ","" + getScrollX());
                if (getScrollX() + scrollX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + scrollX + getWidth() > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }

                scrollBy(scrollX, 0);
                mXLastMove = mXMove;
                break;
            }
            case MotionEvent.ACTION_UP: {
                //判断在第几页
                int index = (getScrollX() + getWidth()/2) / getWidth();
                int dx = getWidth() * index - getScrollX();
                Log.d("touch-up-dx: ","" + dx);
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                Log.d("touch-up-getScrollX: ","" + getScrollX());
                invalidate();
                break;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            Log.d("compute","getCurrX:" + mScroller.getCurrX()+ "  getCurrY:"+ mScroller.getCurrY());
            invalidate();
        }
    }
}
