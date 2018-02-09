package com.bingo.laboratory.refresh;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bingo.laboratory.R;
import com.bingo.laboratory.refresh.header.FadingCircleHeader;
import com.bingo.laboratory.refresh.header.FadingCircleHeaderView;
import com.bingo.laboratory.support.PixelKit;
import com.github.ybq.android.spinkit.SpriteFactory;
import com.github.ybq.android.spinkit.Style;
import com.github.ybq.android.spinkit.sprite.Sprite;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by tuyx on 2017/5/2.
 * Description :
 */

public class RefreshCircleActivity extends AppCompatActivity {


    @Bind(R.id.pcfl_content)
    PtrFrameLayout ptrFrame;


    Handler handler = new Handler(Looper.getMainLooper());
//    @Bind(R.id.fc_loading)
//    FadingCircle fcLoading;
    @Bind(R.id.iv_loading)
    ImageView ivLoading;
//    @Bind(R.id.spin_kit)
//    SpinKitView spinKit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_circle);
        ButterKnife.bind(this);

//        Sprite drawable = SpriteFactory.create(Style.FADING_CIRCLE);
//        drawable.setColor(Color.WHITE);
//        spinKit.setIndeterminateDrawable(drawable);
        Sprite drawable = SpriteFactory.create(Style.FADING_CIRCLE);
        drawable.setColor(Color.WHITE);
//        drawable.start();
        ivLoading.setImageDrawable(drawable);

        // header
        final FadingCircleHeader header = new FadingCircleHeader(this);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, PixelKit.dp2px(this, 15), 0, PixelKit.dp2px(this, 10));
//        header.setUp(ptrFrame);

        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        requestData();
                        ptrFrame.refreshComplete();
                    }
                }, 1800);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }
        });

        // the following are default settings
        ptrFrame.setResistance(1.7f);
        ptrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        ptrFrame.setDurationToClose(200);
        ptrFrame.setDurationToCloseHeader(1000);
        // default is false
        ptrFrame.setPullToRefresh(false);
        // default is true
        ptrFrame.setKeepHeaderWhenRefresh(true);

        ptrFrame.setHeaderView(header);
        ptrFrame.addPtrUIHandler(header);

        // scroll then refresh
        // comment in base fragment
//        ptrFrame.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ptrFrame.autoRefresh();
//            }
//        }, 150);

    }

    protected void setupViews(PtrClassicFrameLayout ptrFrame) {
        ptrFrame.setPullToRefresh(true);
    }

    /**
     * 模拟请求网络
     */
    private void requestData() {

        new Thread() {

            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //模拟一下网络请求失败的情况
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrame.refreshComplete();
                    }
                });

            }
        }.start();
    }
}
