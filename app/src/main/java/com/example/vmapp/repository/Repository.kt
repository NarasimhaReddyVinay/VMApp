package com.example.vmapp.repository

import com.example.vmapp.api.ApiService
import com.example.vmapp.model.people.PeopleResponse
import com.example.vmapp.model.rooms.RoomsResponse

interface Repository{
    suspend fun fetchPeople(): PeopleResponse

    suspend fun fetchRoomsDetails(): RoomsResponse
}
class RepositoryImp(private val apiService: ApiService = ApiService.getApiService()):Repository
{
    override suspend fun fetchPeople(): PeopleResponse {
        val response= apiService.getAllPeople()
        return if (response.isSuccessful){
            response.body()!!
        }else{
            PeopleResponse(emptyList())
        }
    }

    override suspend fun fetchRoomsDetails(): RoomsResponse {
        val response =apiService.getAllRoom()
        return if(response.isSuccessful){
            response.body()!!
        }else{
            RoomsResponse(emptyList())
        }
    }


}