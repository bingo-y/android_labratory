package com.bingo.laboratory.ui.scroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bingo.laboratory.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/21 0021
 * @Description :
 * @VersionName :
 */
public class ScrollActivity extends AppCompatActivity {

    @Bind(R.id.b_scroll_by)
    Button bScrollBy;
    @Bind(R.id.b_scroll_to)
    Button bScrollTo;
    @Bind(R.id.ll_content)
    LinearLayout llContent;
    @Bind(R.id.ll_second)
    LinearLayout llSecond;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.b_scroll_by, R.id.b_scroll_to})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b_scroll_by:
                llSecond.scrollBy(-100, -200);
//                bScrollBy.scrollBy(-100, -200);
                break;
            case R.id.b_scroll_to:
                llSecond.scrollTo(-100, -200);
//                bScrollTo.scrollTo(-100, -200);
                break;
        }
    }
}
