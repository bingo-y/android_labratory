package com.bingo.laboratory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bingo.laboratory.ui.animator.object.PeacockAnimatorActivity;
import com.bingo.laboratory.ui.animator.object.PlayObjectAnimatorActivity;
import com.bingo.laboratory.ui.animator.object.PropertyValueActivity;
import com.bingo.laboratory.ui.animator.value.CharValueAnimatorActivity;
import com.bingo.laboratory.ui.bezier.BezierActivity;
import com.bingo.laboratory.ui.canvas.CanvasActivity;
import com.bingo.laboratory.ui.customview.CustomerViewActivity;
import com.bingo.laboratory.ui.matrix.TransformMatrixActivity;

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
                }
            }
        });
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
