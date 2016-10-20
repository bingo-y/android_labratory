package com.bingo.laboratory.ui.animator.object;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.bingo.laboratory.R;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/20 0020
 * @Description : ObjectAnimator
 * @VersionName :
 */
public class PlayObjectAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv;
    Button bStart;
    Button bStop;
    CustomCircleView ccv;

    ObjectAnimator objectAnimator;
    ObjectAnimator cObjectAnimator;
    AnimatorSet animatorSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);
        tv = (TextView) findViewById(R.id.tv_object);
        bStart = (Button) findViewById(R.id.b_start);
        bStop = (Button) findViewById(R.id.b_end);
        ccv = (CustomCircleView) findViewById(R.id.ccv);

        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_start: {
                startAnimator();
                break;
            }
            case R.id.b_end: {
                stopAnimator();
                break;
            }
        }
    }


    void startAnimator() {
        if (objectAnimator == null) {
            //alpha
//            objectAnimator = ObjectAnimator.ofFloat(tv, "alpha", 1, 0, 1);
            //rotation
//            objectAnimator = ObjectAnimator.ofFloat(tv, "rotation", 0, 45, 0);
//            objectAnimator = ObjectAnimator.ofFloat(tv, "rotationX", 0, 270, 0);
//            objectAnimator = ObjectAnimator.ofFloat(tv, "rotationY", 0, 270, 0);
            //scale
//            objectAnimator = ObjectAnimator.ofFloat(tv, "scaleX", 1, 2, 1);
//            objectAnimator = ObjectAnimator.ofFloat(tv, "scaleY", 1, 2, 1);
            //translation
//            objectAnimator = ObjectAnimator.ofFloat(tv, "translationX", 0, 200, -200, 0);
            objectAnimator = ObjectAnimator.ofFloat(tv, "translationY", 0, 200, -200, 100, -50, 20, 0);
            objectAnimator.setInterpolator(new DecelerateInterpolator());
            objectAnimator.setDuration(3000);
        }
//        objectAnimator.start();
        if (cObjectAnimator == null) {
            cObjectAnimator = ObjectAnimator.ofInt(ccv, "radius", 0, 100);
            cObjectAnimator.setDuration(1000);
        }
//        cObjectAnimator.start();
        if (animatorSet == null) {
            animatorSet = new AnimatorSet();
//            animatorSet.playTogether(objectAnimator, cObjectAnimator);
//            animatorSet.playSequentially(objectAnimator, cObjectAnimator);

            //build
            AnimatorSet.Builder builder = animatorSet.play(objectAnimator).with(cObjectAnimator);
            animatorSet.setDuration(2000);
        }
        animatorSet.start();
    }

    void stopAnimator() {
        if (objectAnimator != null) {
            objectAnimator.end();
        }
    }
}
