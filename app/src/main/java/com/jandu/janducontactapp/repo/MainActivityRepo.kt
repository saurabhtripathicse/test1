package com.jandu.janducontactapp.repo

import android.app.Application
import android.content.Context
import android.util.Log
import com.jandu.janducontactapp.MyApplication
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class MainActivityRepo @Inject constructor(val applicationContext: MyApplication) {

    fun getContactData() : String{

        Log.i("APPDATA", applicationContext.toString())

        return applicationContext.toString()
    }

}