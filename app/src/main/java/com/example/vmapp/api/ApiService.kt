package com.example.vmapp.api

import com.example.vmapp.model.people.PeopleItem
import com.example.vmapp.model.people.PeopleResponse
import com.example.vmapp.model.rooms.RoomsItem
import com.example.vmapp.model.rooms.RoomsResponse
import com.example.vmapp.util.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("v1/people")
    suspend fun getAllPeople(): Response<PeopleResponse>

    @GET("v1/rooms")
    suspend fun getAllRoom(): Response<RoomsResponse>

    companion object {
        private var instance: ApiService? = null
        fun getApiService(): ApiService{
            if (instance == null){
                instance = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return instance!!
        }
    }
}