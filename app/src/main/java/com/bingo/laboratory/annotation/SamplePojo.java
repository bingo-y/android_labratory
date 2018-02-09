package com.bingo.laboratory.annotation;

import com.example.PojoString;

/**
 * Author by tuyx
 *
 * @Created at : 2016/11/5 0005
 * @Description :
 * @VersionName :
 */
@PojoString
public class SamplePojo {
    public String s1;
    public String s2;

    public SamplePojo(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public String toString() {
//        return StringUtil.createString(this);
        return "";
    }
}
