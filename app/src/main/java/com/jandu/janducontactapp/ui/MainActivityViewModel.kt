package com.jandu.janducontactapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jandu.janducontactapp.repo.MainActivityRepo
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(var repo: MainActivityRepo)  : ViewModel() {

    var repo1 = MutableLiveData<String>()

    fun getData() : MutableLiveData<String>{

         repo1.postValue(repo.getContactData())

        return repo1

    }

}