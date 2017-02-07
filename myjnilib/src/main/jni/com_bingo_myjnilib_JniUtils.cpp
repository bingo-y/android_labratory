//
// Created by Administrator on 2016/11/10 0010.
//

#include "com_bingo_myjnilib_JniUtils.h"

JNIEXPORT jstring JNICALL Java_com_bingo_myjnilib_JniUtils_getStringFromC
        (JNIEnv * env, jclass obj){
    return env->NewStringUTF("这里是C++代码");
}
