package com.example.vmapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.vmapp.adapter.RecyclerViewAdapter
import com.example.vmapp.databinding.FragmentRoomsBinding
import com.example.vmapp.repository.RepositoryImp
import com.example.vmapp.viewmodel.ViewModel


class RoomsFragment : Fragment() {

    private val adapter: RecyclerViewAdapter by lazy{
        RecyclerViewAdapter()
    }

    private var _binding: FragmentRoomsBinding? = null
    private val binding: FragmentRoomsBinding get()= _binding!!

    private val viewModel:ViewModel by lazy {
        object : ViewModelProvider.Factory{
            override fun <T : androidx.lifecycle.ViewModel?> create(modelClass: Class<T>): T {
                return ViewModel(RepositoryImp()) as T
            }
        }.create(ViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomsBinding.inflate(layoutInflater)

        setListRooms()
        observeRooms()

        viewModel.getRoomLists()

        return binding.root
    }

    private fun observeRooms() {
        viewModel.roomsLists.observe(viewLifecycleOwner){
            adapter.setRoomList(it)
        }
    }

    private fun setListRooms() {
        binding.rvRooms.setHasFixedSize(true)
        binding.rvRooms.adapter = adapter
    }
}