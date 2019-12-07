package com.jandu.janducontactapp.di.module

import androidx.lifecycle.ViewModel
import com.jandu.janducontactapp.factory.ViewModelKey
import com.jandu.janducontactapp.ui.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

}