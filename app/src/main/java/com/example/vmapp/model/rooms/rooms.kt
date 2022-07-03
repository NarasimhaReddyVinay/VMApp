package com.example.vmapp.model.rooms


import com.google.gson.annotations.SerializedName

data class RoomsResponse(
    @SerializedName("rooms")
    val rooms: List<Any>
)