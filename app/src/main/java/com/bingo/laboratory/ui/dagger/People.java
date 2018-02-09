package com.bingo.laboratory.ui.dagger;

import javax.inject.Inject;

import dagger.Provides;

/**
 * Created by tuyx on 2017/10/12.
 * Description :
 */

public class People {

    String name;

    @Inject
    public People(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
