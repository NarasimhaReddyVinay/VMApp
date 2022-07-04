package com.example.vmapp.model.rooms

data class RoomsItem(
    val created_at: String,
    val id: String,
    val is_occupied: Boolean,
    val max_occupancy: Int,
    val name: String
)