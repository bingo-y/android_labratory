package com.bingo.laboratory.arouter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bingo.laboratory.R;
import com.bingo.laboratory.cst.UrlConstant;

/**
 * Created by tuyx on 2017/4/19.
 * Description :
 */
@Route(path = UrlConstant.ACTIVITY_TEST1)
public class ArouterTest1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter_test1);
    }
}
