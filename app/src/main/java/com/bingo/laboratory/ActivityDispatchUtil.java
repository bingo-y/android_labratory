package com.bingo.laboratory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Author by tuyx
 *
 * @Created at : 2016/10/10 0010
 * @Description :
 * @VersionName :
 */
public class ActivityDispatchUtil {
    public static void startActivity(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }
}
