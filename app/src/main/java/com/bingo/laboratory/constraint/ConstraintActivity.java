package com.bingo.laboratory.constraint;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.AutoTransition;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bingo.laboratory.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author bingo.
 * @date Create on 2018/6/7.
 * @Description
 */
public class ConstraintActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.cl_content)
    ConstraintLayout clContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contraint);
        ButterKnife.bind(this);


    }


    @OnClick(R.id.cl_content)
    public void onClick() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this, R.layout.activity_contraint_second);

        Transition transition = new AutoTransition();
        transition.setDuration(1000);
        TransitionManager.beginDelayedTransition(clContent, transition);
        constraintSet.applyTo(clContent);
    }
}
