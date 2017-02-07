package com.bingo.myjnilib;

/**
 * Author by tuyx
 *
 * @Created at : 2016/11/10 0010
 * @Description :
 * @VersionName :
 */
public class JniUtils {
    static {
        System.loadLibrary("myjnilib");
    }
    public static native String getStringFromC();
}
