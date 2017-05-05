package com.bingo.laboratory.arouter.interceptor;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.bingo.laboratory.cst.UrlConstant;

/**
 * Created by tuyx on 2017/4/19.
 * Description :
 */
@Interceptor(priority = 5)
public class TestInterceptor implements IInterceptor {
    Context mContext;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        int extra = postcard.getExtra();
        if (extra == UrlConstant.LoginExtra) {
            Toast.makeText(mContext, "跳转登录", Toast.LENGTH_SHORT).show();
            callback.onInterrupt(new Throwable("未登录"));
        } else {
            Toast.makeText(mContext, "正常拦截", Toast.LENGTH_SHORT).show();
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        mContext = context;
        Log.e("testService", TestInterceptor.class.getName() + " has init.");
    }
}
