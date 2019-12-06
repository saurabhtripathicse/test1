package com.jandu.janducontactapp.di

import com.jandu.janducontactapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [])
    abstract fun mainActivityInjector(): MainActivity

}