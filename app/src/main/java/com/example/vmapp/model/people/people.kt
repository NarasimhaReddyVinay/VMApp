package com.example.vmapp.model.people


import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @SerializedName("people")
    val people: List<Any>
)