package com.example.vmapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vmapp.adapter.RecyclerViewAdapter
import com.example.vmapp.databinding.FragmentRoomsBinding
import com.example.vmapp.model.rooms.RoomsItem
import com.example.vmapp.util.UIState
import com.example.vmapp.viewmodel.FragmentsViewModel



class RoomsFragment : FragmentsViewModel() {


    private val binding: FragmentRoomsBinding by lazy {
        FragmentRoomsBinding.inflate(layoutInflater)
    }
    lateinit var recyclerViewAdapter: RecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recyclerViewAdapter = RecyclerViewAdapter()
        binding.rvRooms.adapter = recyclerViewAdapter
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {

        viewModel.roomsLists.observe(viewLifecycleOwner) { state ->
            when (state) {

                is UIState.Success<*> -> {
                    binding.apply {
                        RoomProgressbar.visibility = View.GONE

                        val rooms = state.response
                        //recyclerViewAdapter.setRoomList(rooms as List<RoomsModelItem>)
                        recyclerViewAdapter.setRoomList(rooms as List<RoomsItem>)

                    }
                }
                is UIState.Error -> {
                    binding.apply {
                        RoomProgressbar.visibility = View.GONE
                        tvRoomError.text = state.exception.message
                    }
                }
                is UIState.Loading -> {
                    binding.RoomProgressbar.visibility = View.GONE
                }
            }
        }
    }

}