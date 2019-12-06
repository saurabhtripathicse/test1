package com.jandu.janducontactapp.di

import android.app.Application
import com.jandu.janducontactapp.MyApplication
import com.jandu.janducontactapp.ViewModelModule
import com.jandu.janducontactapp.repo.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ContactModule::class,
        ActivityBindingModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): AppComponent
    }

}