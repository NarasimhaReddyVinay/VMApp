package com.example.vmapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vmapp.model.people.PeopleItem
import com.example.vmapp.model.rooms.RoomsItem
import com.example.vmapp.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(val repository: Repository) : ViewModel() {

    private val _peopleList = MutableLiveData<ArrayList<PeopleItem>>()
    val peopleList: MutableLiveData<ArrayList<PeopleItem>> get() = _peopleList

    private val _roomsLists : MutableLiveData<ArrayList<RoomsItem>> = MutableLiveData()
    val roomsLists : LiveData<ArrayList<RoomsItem>> get() = _roomsLists


    fun getRoomLists() {
        CoroutineScope(Dispatchers.Main).launch {
            val response = repository.fetchRoomsDetails()
            _roomsLists.postValue(response.rooms as ArrayList<RoomsItem>?)
        }
    }

    fun getPeopleLists() {
        CoroutineScope(Dispatchers.Main).launch{
            val response = repository.fetchPeople()
            _peopleList.postValue(response.people as ArrayList<PeopleItem>?)
        }

    }

}