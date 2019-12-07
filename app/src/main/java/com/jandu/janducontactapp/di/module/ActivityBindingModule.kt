package com.jandu.janducontactapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.jandu.janducontactapp.factory.ViewModelFactory
import com.jandu.janducontactapp.ui.MainActivity
import com.jandu.janducontactapp.ui.MainActivityViewModel
import dagger.Binds
import dagger.Module

import dagger.android.ContributesAndroidInjector



@Module
public abstract class ActivityBindingModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity


}