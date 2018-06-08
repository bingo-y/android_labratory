package com.bingo.laboratory.ui.dagger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bingo.laboratory.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuyx on 2017/10/12.
 * Description :
 */

public class DaggerActivity extends AppCompatActivity {


    @BindView(R.id.tv_name)
    TextView tvName;

    @Inject
    People people;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        ButterKnife.bind(this);

        DaggerPeopleComponent.builder()
                .build()
                .inject(this);

        tvName.setText(people.getName());
    }
}
