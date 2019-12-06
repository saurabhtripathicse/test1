package com.jandu.janducontactapp.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jandu.janducontactapp.model.ContactModel
import com.jandu.janducontactapp.repo.ContactListRepo
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(contactListRepo: ContactListRepo, application: DaggerApplication): ViewModel() {


    fun data(){

    }


}