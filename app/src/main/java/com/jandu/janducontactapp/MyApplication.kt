package com.jandu.janducontactapp

import android.app.Application
import com.jandu.janducontactapp.di.AppComponent
import com.jandu.janducontactapp.di.DaggerAppComponent
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MyApplication : DaggerApplication()  {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? =
        DaggerAppComponent.builder().application(this).build()

}