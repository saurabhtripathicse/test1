package com.jandu.janducontactapp.repo

import com.jandu.janducontactapp.di.ContactModule
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerApplication

import javax.inject.Singleton



@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideContactRepo(application: DaggerApplication, contactModule: ContactModule)
    : ContactListRepo{
        return ContactListRepo(application, contactModule)
    }


}