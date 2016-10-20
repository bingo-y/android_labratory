package com.bingo.laboratory.ui.animator.object;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bingo.laboratory.R;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/20 0020
 * @Description :
 * @VersionName :
 */
public class PropertyValueActivity extends AppCompatActivity implements View.OnClickListener {

    Button bStart;
    Button bStop;
    ImageView iv;

    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_value);
        bStart = (Button) findViewById(R.id.b_start);
        bStop = (Button) findViewById(R.id.b_end);
        iv = (ImageView) findViewById(R.id.iv_cheery);

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
            //旋转帧
            Keyframe keyframeRotate1 = Keyframe.ofFloat(0, 0);
            Keyframe keyframeRotate2 = Keyframe.ofFloat(0.1f, -20);
            Keyframe keyframeRotate3 = Keyframe.ofFloat(0.2f, 20);
            Keyframe keyframeRotate4 = Keyframe.ofFloat(0.3f, -20);
            Keyframe keyframeRotate5 = Keyframe.ofFloat(0.4f, 20);
            Keyframe keyframeRotate6 = Keyframe.ofFloat(0.5f, -20);
            Keyframe keyframeRotate7 = Keyframe.ofFloat(0.6f, 20);
            Keyframe keyframeRotate8 = Keyframe.ofFloat(0.7f, -20);
            Keyframe keyframeRotate9 = Keyframe.ofFloat(0.8f, 20);
            Keyframe keyframeRotate10 = Keyframe.ofFloat(0.9f, -20);
            Keyframe keyframeRotate11 = Keyframe.ofFloat(1, 0);
            PropertyValuesHolder propertyValuesHolderRotate = PropertyValuesHolder.ofKeyframe("rotation",
                    keyframeRotate1,
                    keyframeRotate2,
                    keyframeRotate3,
                    keyframeRotate4,
                    keyframeRotate5,
                    keyframeRotate6,
                    keyframeRotate7,
                    keyframeRotate8,
                    keyframeRotate9,
                    keyframeRotate10,
                    keyframeRotate11);
            //x轴放大帧
            Keyframe keyframeScaleX1 = Keyframe.ofFloat(0, 1);
            Keyframe keyframeScaleX2 = Keyframe.ofFloat(0.1f, 1.2f);
            Keyframe keyframeScaleX3 = Keyframe.ofFloat(0.2f, 1.2f);
            Keyframe keyframeScaleX4 = Keyframe.ofFloat(0.3f, 1.2f);
            Keyframe keyframeScaleX5 = Keyframe.ofFloat(0.4f, 1.2f);
            Keyframe keyframeScaleX6 = Keyframe.ofFloat(0.5f, 1.2f);
            Keyframe keyframeScaleX7 = Keyframe.ofFloat(0.6f, 1.2f);
            Keyframe keyframeScaleX8 = Keyframe.ofFloat(0.7f, 1.2f);
            Keyframe keyframeScaleX9 = Keyframe.ofFloat(0.8f, 1.2f);
            Keyframe keyframeScaleX10 = Keyframe.ofFloat(0.9f, 1.2f);
            Keyframe keyframeScaleX11 = Keyframe.ofFloat(1, 1);
            PropertyValuesHolder propertyValuesHolderScaleX = PropertyValuesHolder.ofKeyframe("scaleX",
                    keyframeScaleX1,
                    keyframeScaleX2,
                    keyframeScaleX3,
                    keyframeScaleX4,
                    keyframeScaleX5,
                    keyframeScaleX6,
                    keyframeScaleX7,
                    keyframeScaleX8,
                    keyframeScaleX9,
                    keyframeScaleX10,
                    keyframeScaleX11);

            //y轴放大帧
            Keyframe keyframeScaleY1 = Keyframe.ofFloat(0, 1);
            Keyframe keyframeScaleY2 = Keyframe.ofFloat(0.1f, 1.2f);
            Keyframe keyframeScaleY3 = Keyframe.ofFloat(0.2f, 1.2f);
            Keyframe keyframeScaleY4 = Keyframe.ofFloat(0.3f, 1.2f);
            Keyframe keyframeScaleY5 = Keyframe.ofFloat(0.4f, 1.2f);
            Keyframe keyframeScaleY6 = Keyframe.ofFloat(0.5f, 1.2f);
            Keyframe keyframeScaleY7 = Keyframe.ofFloat(0.6f, 1.2f);
            Keyframe keyframeScaleY8 = Keyframe.ofFloat(0.7f, 1.2f);
            Keyframe keyframeScaleY9 = Keyframe.ofFloat(0.8f, 1.2f);
            Keyframe keyframeScaleY10 = Keyframe.ofFloat(0.9f, 1.2f);
            Keyframe keyframeScaleY11 = Keyframe.ofFloat(1, 1);
            PropertyValuesHolder propertyValuesHolderScaleY = PropertyValuesHolder.ofKeyframe("scaleY",
                    keyframeScaleY1,
                    keyframeScaleY2,
                    keyframeScaleY3,
                    keyframeScaleY4,
                    keyframeScaleY5,
                    keyframeScaleY6,
                    keyframeScaleY7,
                    keyframeScaleY8,
                    keyframeScaleY9,
                    keyframeScaleY10,
                    keyframeScaleY11);

            objectAnimator = ObjectAnimator.ofPropertyValuesHolder(iv, propertyValuesHolderRotate, propertyValuesHolderScaleX, propertyValuesHolderScaleY);
            objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            objectAnimator.setDuration(3000);
        }
        objectAnimator.start();
    }

    void stopAnimator() {
        if (objectAnimator != null) {
            objectAnimator.end();
        }
    }
}
