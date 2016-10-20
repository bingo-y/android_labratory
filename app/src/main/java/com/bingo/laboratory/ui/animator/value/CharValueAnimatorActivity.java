package com.bingo.laboratory.ui.animator.value;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.bingo.laboratory.R;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/20 0020
 * @Description : ValueAnimator
 * @VersionName :
 */
public class CharValueAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv;
    Button bStart;
    Button bStop;

    ValueAnimator charAnimator;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_value_animator);
        tv = (TextView) findViewById(R.id.tv_content);
        bStart = (Button) findViewById(R.id.b_start);
        bStop = (Button) findViewById(R.id.b_end);

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
        if (charAnimator == null) {
            charAnimator = ValueAnimator.ofObject(new CharEvaluator(), new Character('A'), new Character('Z'));
            charAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    char c = (char) animation.getAnimatedValue();
                    tv.setText(String.valueOf(c));
                }
            });
            charAnimator.setInterpolator(new AccelerateInterpolator());
            charAnimator.setDuration(3000);
        }
        charAnimator.start();
    }

    void stopAnimator() {
        if (charAnimator != null) {
            charAnimator.cancel();
        }
    }

    public static class CharEvaluator implements TypeEvaluator<Character> {

        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int start = (int) startValue;
            int end = (int) endValue;
            int current = (int) (start + (end - start)*fraction);
            return (char) current;
        }
    }
}
