package com.jandu.janducontactapp.di

import com.jandu.janducontactapp.MyApplication
import com.jandu.janducontactapp.di.module.ActivityBindingModule
import com.jandu.janducontactapp.di.module.ContextModule
import com.jandu.janducontactapp.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        ContextModule::class,
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

    override fun inject(app: MyApplication)

}