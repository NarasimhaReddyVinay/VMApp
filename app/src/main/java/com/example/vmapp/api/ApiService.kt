package com.example.vmapp.api

import com.example.vmapp.model.people.People
import com.example.vmapp.model.rooms.RoomsItem
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("v1/people")
    suspend fun getPeople(): Response<People>

    @GET("v1/rooms")
    suspend fun getRoom(): Response<List<RoomsItem>>

}