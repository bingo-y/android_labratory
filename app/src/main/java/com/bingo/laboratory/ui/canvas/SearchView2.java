package com.bingo.laboratory.ui.canvas;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/19 0019
 * @Description :
 * @VersionName :
 */
public class SearchView2 extends View {
    //画笔
    Paint mPaint;

    //旋转
    Path searchPath;
    //外圆路径
    Path circlePath;

    // 测量Path 并截取部分的工具
    PathMeasure pathMeasure;

    int width;
    int height;

    // 默认的动效周期 2s
    int defaultDuration = 2000;
    //当前状态
    State mAnimatorState;

    //搜索旋转动画
    ValueAnimator searchingAnimator;
    //开始动画
    ValueAnimator startAnimator;
    //结束动画
    ValueAnimator endAnimator;


    // 动效过程监听器
    ValueAnimator.AnimatorUpdateListener mUpdateListener;
    Animator.AnimatorListener mAnimatorListener;

    // 动画数值(用于控制动画状态,因为同一时间内只允许有一种状态出现,具体数值处理取决于当前状态)
    float mAnimatorValue = 0;

    Handler mHandler;

    //搜索中结束标志
    int loopCount = 0;

    enum State {
        NONE,
        STARTING,
        SEARCHING,
        ENDING
    }

    public SearchView2(Context context) {
        super(context);
        init();
    }

    void init() {
        //初始化画笔
        {
            mPaint = new Paint();
            mPaint.setStrokeWidth(15);
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setDither(true);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setColor(Color.WHITE);
        }
        //路径
        {
            circlePath = new Path();
            searchPath = new Path();

            RectF rectF = new RectF(-100, -100, 100, 100);
            circlePath.addArc(rectF, 45, 359.9f);

            RectF searchRect = new RectF(-50, -50, 50, 50);
            searchPath.addArc(searchRect,45, 359.9f);

            float[] pos = new float[2];

            pathMeasure = new PathMeasure();
            pathMeasure.setPath(circlePath, false);
            pathMeasure.getPosTan(0, pos, null);

            searchPath.lineTo(pos[0], pos[1]);

            Log.i("TAG", "pos=" + pos[0] + ":" + pos[1]);
        }
        //handler
        {
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (mAnimatorState) {
                        case NONE: {

                            break;
                        }
                        case STARTING: {
                            mAnimatorState = State.SEARCHING;
                            startAnimator.removeAllUpdateListeners();
                            searchingAnimator.start();
                            break;
                        }
                        case SEARCHING: {
                            if (loopCount < 2) {
                                loopCount ++;
                                //继续
                                searchingAnimator.start();
                            } else {
//                                searchingAnimator.removeAllUpdateListeners();
                                mAnimatorState = State.ENDING;
                                endAnimator.start();
                            }
                            break;
                        }
                        case ENDING: {
//                            endAnimator.removeAllUpdateListeners();
                            mAnimatorState = State.NONE;
                            break;
                        }
                    }
                }
            };
        }
        //动画监听
        {
            mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mAnimatorValue = (float) animation.getAnimatedValue();
                    invalidate();
                }
            };

            mAnimatorListener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    // getHandle发消息通知动画状态更新
                    mHandler.sendEmptyMessage(0);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            };
        }
        //动画
        {
            searchingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
            startAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
            endAnimator = ValueAnimator.ofFloat(1, 0).setDuration(defaultDuration);

            searchingAnimator.addUpdateListener(mUpdateListener);
            startAnimator.addUpdateListener(mUpdateListener);
            endAnimator.addUpdateListener(mUpdateListener);

            searchingAnimator.addListener(mAnimatorListener);
            startAnimator.addListener(mAnimatorListener);
            endAnimator.addListener(mAnimatorListener);

        }
        //开始动画
        mAnimatorState = State.STARTING;
        startAnimator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width / 2, height / 2);
        canvas.drawColor(Color.parseColor("#0082D7"));

        mPaint.setColor(Color.WHITE);

        switch (mAnimatorState) {
            case NONE: {
                canvas.drawPath(searchPath, mPaint);
                break;
            }
            case STARTING: {
                pathMeasure.setPath(searchPath, false);
                Path dst = new Path();
                pathMeasure.getSegment(pathMeasure.getLength()*mAnimatorValue, pathMeasure.getLength(), dst, true);
                canvas.drawPath(dst, mPaint);
                break;
            }
            case SEARCHING: {
                pathMeasure.setPath(circlePath, false);
                Path dst2 = new Path();
                float stop = pathMeasure.getLength() * mAnimatorValue;
                float start = (float) (stop - ((0.5 -  Math.abs(mAnimatorValue - 0.5)) * 200f));
                pathMeasure.getSegment(start, stop, dst2, true);
                canvas.drawPath(dst2, mPaint);
                break;
            }
            case ENDING: {
                pathMeasure.setPath(searchPath, false);
                Path dst3 = new Path();
                pathMeasure.getSegment(pathMeasure.getLength()*mAnimatorValue, pathMeasure.getLength(), dst3, true);
                canvas.drawPath(dst3, mPaint);
                break;
            }
        }

    }
}

