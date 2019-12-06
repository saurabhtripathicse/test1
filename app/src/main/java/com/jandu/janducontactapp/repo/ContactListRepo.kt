package com.jandu.janducontactapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jandu.janducontactapp.di.ContactModule
import com.jandu.janducontactapp.model.ContactModel
import dagger.android.support.DaggerApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactListRepo @Inject constructor(
    private val application: DaggerApplication,
    private val contactModule: ContactModule){




    fun loadContactList(): LiveData<List<ContactModel>>{

        val data1 =  MutableLiveData<List<ContactModel>>()

        GlobalScope.launch(Dispatchers.Main) {



            val data =  async(Dispatchers.IO){

                return@async contactModule.getContacts(application)

            }.await()

            data1.postValue(data)

        }

        return data1
    }
}