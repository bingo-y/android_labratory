package com.bingo.laboratory.ui.dagger;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tuyx on 2017/10/12.
 * Description :
 */
@Module
public class PeopleModule {

    @Provides
    public People providerPeople(String name) {
        return new People("from provider module");
    }

    @Provides
    public String providerName() {
        return "provider module";
    }

}
