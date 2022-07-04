package com.example.vmapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vmapp.repository.Repository
import com.example.vmapp.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _peopleList = MutableLiveData<UIState>()
    val peopleList: LiveData<UIState> get() = _peopleList

    private val _roomsLists :MutableLiveData<UIState> = MutableLiveData()
    val roomsLists : LiveData<UIState> get() = _roomsLists

    init {
        getPeopleLists()
        getRoomLists()
    }
    private fun getPeopleLists() {
       viewModelScope.launch {
            repository.fetchPeople().collect {
                _peopleList.postValue(it)
            }
        }

    }

  private  fun getRoomLists(){
        viewModelScope.launch {
            repository.fetchRooms().collect{
                _roomsLists.postValue(it)
            }
        }
    }
}

