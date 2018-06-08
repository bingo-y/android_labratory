package com.bingo.laboratory.ui.parcel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bingo.laboratory.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuyx on 2017/9/27.
 * Description :
 */

public class PacelActivity extends AppCompatActivity {

    @BindView(R.id.tv_parcel)
    TextView tvParcel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacel);
        ButterKnife.bind(this);

        House house = getIntent().getParcelableExtra("house");
        tvParcel.setText(String.format("名称：%s；地址：%s；建筑面积：%d；", house.getName(), house.getAddress(), house.getSize()));
    }
}
