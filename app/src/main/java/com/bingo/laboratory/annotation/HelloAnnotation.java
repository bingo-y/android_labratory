package com.bingo.laboratory.annotation;

/**
 * Author by tuyx
 *
 * @Created at : 2016/11/4 0004
 * @Description :
 * @VersionName :
 */
public class HelloAnnotation {
    @TestAnnotation(value = "hello annotation", value2 = {"," ,"i", "know", "you"})
    String call;

    @TestAnnotation("this is a method")
    private void showCall() {

    }
}
