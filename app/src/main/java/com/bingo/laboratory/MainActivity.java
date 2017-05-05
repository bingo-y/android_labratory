package com.bingo.laboratory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bingo.laboratory.annotation.HelloAnnotation;
import com.bingo.laboratory.annotation.SamplePojo;
import com.bingo.laboratory.annotation.TestAnnotation;
import com.bingo.laboratory.arouter.ArouterTest1Activity;
import com.bingo.laboratory.cst.UrlConstant;
import com.bingo.laboratory.live.LivePlayActivity;
import com.bingo.laboratory.live.VideoEabledWebViewActivity;
import com.bingo.laboratory.refresh.RefreshLoadMoreActivity;
import com.bingo.laboratory.toolbar.TranslucentToolbarActivity;
import com.bingo.laboratory.ui.animator.object.PeacockAnimatorActivity;
import com.bingo.laboratory.ui.animator.object.PlayObjectAnimatorActivity;
import com.bingo.laboratory.ui.animator.object.PropertyValueActivity;
import com.bingo.laboratory.ui.animator.value.CharValueAnimatorActivity;
import com.bingo.laboratory.ui.bezier.BezierActivity;
import com.bingo.laboratory.ui.canvas.CanvasActivity;
import com.bingo.laboratory.ui.customview.CustomerViewActivity;
import com.bingo.laboratory.ui.matrix.TransformMatrixActivity;
import com.bingo.laboratory.ui.scroll.ScrollActivity;
import com.bingo.laboratory.ui.scroll.VPScrollActivity;
import com.bingo.myjnilib.JniUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    static List<String> list;
    static {
        list = new ArrayList<>();
        list.add("打开matrix");
        list.add("打开canvas");
        list.add("打开customer_view");
        list.add("打开bezier_view");
        list.add("打开char_value_animator");
        list.add("打开play_object_animator");
        list.add("打开property_value");
        list.add("打开peacock_animator");
        list.add("打开scroll");
        list.add("打开vp_scroll");
        list.add("resolve annotation");
        list.add("process annotation");
        list.add("live play");
        list.add("load jni");
        list.add("open arouter");
        list.add("测试toolbar");
        list.add("下拉刷新上拉加载");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv_entrances);
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_main, list));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0 : {
                        ActivityDispatchUtil.startActivity(MainActivity.this, TransformMatrixActivity.class);
                        break;
                    }
                    case 1 : {
                        ActivityDispatchUtil.startActivity(MainActivity.this, CanvasActivity.class);
                        break;
                    }
                    case 2 : {
                        ActivityDispatchUtil.startActivity(MainActivity.this, CustomerViewActivity.class);
                        break;
                    }
                    case 3 : {
                        ActivityDispatchUtil.startActivity(MainActivity.this, BezierActivity.class);
                        break;
                    }
                    case 4 : {
                        ActivityDispatchUtil.startActivity(MainActivity.this, CharValueAnimatorActivity.class);
                        break;
                    }
                    case 5 : {
                        ActivityDispatchUtil.startActivity(MainActivity.this, PlayObjectAnimatorActivity.class);
                        break;
                    }
                    case 6 : {
                        ActivityDispatchUtil.startActivity(MainActivity.this, PropertyValueActivity.class);
                        break;
                    }
                    case 7 : {
                        ActivityDispatchUtil.startActivity(MainActivity.this, PeacockAnimatorActivity.class);
                        break;
                    }
                    case 8 : {
                        ActivityDispatchUtil.startActivity(MainActivity.this, ScrollActivity.class);
                        break;
                    }
                    case 9 : {
                        ActivityDispatchUtil.startActivity(MainActivity.this, VPScrollActivity.class);
                        break;
                    }
                    case 10 : {
                        resolveHelloAnnotation();
                        break;
                    }
                    case 11: {
                        Toast.makeText(MainActivity.this, new SamplePojo("aaa", "bbb").toString(), Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 12: {
                        ActivityDispatchUtil.startActivity(MainActivity.this, VideoEabledWebViewActivity.class);
                        break;
                    }
                    case 13: {
                        Toast.makeText(MainActivity.this, JniUtils.getStringFromC(), Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 14: {
                        ARouter.getInstance().build(UrlConstant.ACTIVITY_TEST1).navigation();
//                        ActivityDispatchUtil.startActivity(MainActivity.this, ArouterTest1Activity.class);
                        break;
                    }
                    case 15: {
                        ActivityDispatchUtil.startActivity(MainActivity.this, TranslucentToolbarActivity.class);
                        break;
                    }
                    case 16: {
                        ActivityDispatchUtil.startActivity(MainActivity.this, RefreshLoadMoreActivity.class);
                        break;
                    }
                }
            }
        });
    }

    void resolveHelloAnnotation() {
        try {
//            Class cls = Class.forName("com.bingo.laboratory.annotation.HelloAnnotation");
            Class cls = HelloAnnotation.class;
            Field field =  cls.getDeclaredField("call");
            Method[] methods = cls.getDeclaredMethods();
            StringBuilder stringBuilder = new StringBuilder();
            if (field != null) {
                TestAnnotation testAnnotation = field.getAnnotation(TestAnnotation.class);
                String value = testAnnotation.value();
                String[] value2 = testAnnotation.value2();
                stringBuilder.append(value);
                for (int i = 0; i < value2.length; i ++){
                    stringBuilder.append(" ").append(value2[i]);
                }

            }
            if (methods != null && methods.length > 0) {
                for (Method method : methods) {
                    if (method.isAnnotationPresent(TestAnnotation.class)) {
                        TestAnnotation testAnnotation = method.getAnnotation(TestAnnotation.class);
                        String value = testAnnotation.value();
                        String[] value2 = testAnnotation.value2();
                        stringBuilder.append("\n").append(value);
                        for (int i = 0; i < value2.length; i ++){
                            stringBuilder.append(" ").append(value2[i]);
                        }
                    }
                }
            }
            Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_matrix : {
//                ActivityDispatchUtil.startActivity(MainActivity.this, TransformMatrixActivity.class);
//                break;
//            }
//            case R.id.tv_canvas : {
//                ActivityDispatchUtil.startActivity(MainActivity.this, CanvasActivity.class);
//                break;
//            }
//            case R.id.tv_customer_view : {
//                ActivityDispatchUtil.startActivity(MainActivity.this, CustomerViewActivity.class);
//                break;
//            }
//            case R.id.tv_bezier_view : {
////                ActivityDispatchUtil.startActivity(MainActivity.this, BezierActivity.class);
//                ActivityDispatchUtil.startActivity(MainActivity.this, CharValueAnimatorActivity.class);
//                break;
//            }
//        }
//    }
}
