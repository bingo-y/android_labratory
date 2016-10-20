package com.bingo.laboratory.ui.animator.object;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bingo.laboratory.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/20 0020
 * @Description :
 * @VersionName :
 */
public class PeacockAnimatorActivity extends AppCompatActivity {

    @Bind(R.id.menu)
    Button menu;
    @Bind(R.id.item1)
    Button item1;
    @Bind(R.id.item2)
    Button item2;
    @Bind(R.id.item3)
    Button item3;
    @Bind(R.id.item4)
    Button item4;
    @Bind(R.id.item5)
    Button item5;

    boolean isMenuOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peacock_animator);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.menu, R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu:
                if (isMenuOpen) {
                    doCloseAnimation(item1, 0, 5, 300);
                    doCloseAnimation(item2, 1, 5, 300);
                    doCloseAnimation(item3, 2, 5, 300);
                    doCloseAnimation(item4, 3, 5, 300);
                    doCloseAnimation(item5, 4, 5, 300);
                    isMenuOpen = false;
                } else {
                    doOpenAnimation(item1, 0, 5, 300);
                    doOpenAnimation(item2, 1, 5, 300);
                    doOpenAnimation(item3, 2, 5, 300);
                    doOpenAnimation(item4, 3, 5, 300);
                    doOpenAnimation(item5, 4, 5, 300);
                    isMenuOpen = true;
                }
                break;
            case R.id.item1:

                break;
            case R.id.item2:

                break;
            case R.id.item3:

                break;
            case R.id.item4:

                break;
            case R.id.item5:

                break;
        }
    }

    void doOpenAnimation(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        // Math 中根据度数得到弧度值的函数
        double degree = Math.toRadians(90)/(total - 1)* index;
        float x = -(float) (Math.sin(degree) * radius);
        float y = -(float) (Math.cos(degree) * radius);
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, x),
                ObjectAnimator.ofFloat(view, "translationY", 0, y),
                ObjectAnimator.ofFloat(view, "scaleX", 0, 1),
                ObjectAnimator.ofFloat(view, "scaleY", 0, 1),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1)

        );
        animatorSet.setDuration(1000);
        animatorSet.start();

    }

    void doCloseAnimation(final View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        double degree = Math.toRadians(90)/(total - 1)* index;
        float x = -(float) (Math.sin(degree) * radius);
        float y = -(float) (Math.cos(degree) * radius);
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", x, 0),
                ObjectAnimator.ofFloat(view, "translationY", y, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1, 0),
                ObjectAnimator.ofFloat(view, "scaleY", 1, 0),
                ObjectAnimator.ofFloat(view, "alpha", 1, 0)

        );
        animatorSet.setDuration(1000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
