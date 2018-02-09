package com.bingo.laboratory.ui.dagger;

import dagger.Component;

/**
 * Created by tuyx on 2017/10/12.
 * Description :
 */
@Component(modules = {PeopleModule.class})
public interface PeopleComponent {

    void inject(DaggerActivity daggerActivity);

}
