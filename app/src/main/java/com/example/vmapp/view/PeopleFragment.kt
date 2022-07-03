package com.example.vmapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.vmapp.adapter.RecyclerViewAdapter
import com.example.vmapp.databinding.FragmentPeopleBinding
import com.example.vmapp.repository.RepositoryImp
import com.example.vmapp.viewmodel.ViewModel


class PeopleFragment : Fragment() {

    private val adapter: RecyclerViewAdapter by lazy{
        RecyclerViewAdapter()
    }

    private var _binding: FragmentPeopleBinding? = null
    private val binding: FragmentPeopleBinding get()= _binding!!

    private val viewModel: ViewModel by lazy{
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
        _binding = FragmentPeopleBinding.inflate(layoutInflater)

        setListPeople()
        observePeople()

        viewModel.getPeopleLists()

        return binding.root
    }

    private fun observePeople() {
        viewModel.peopleList.observe(viewLifecycleOwner){
            adapter.setPeopleLists(it)
        }
    }

    private fun setListPeople() {
        binding.rvPeople.setHasFixedSize(true)
        binding.rvPeople.adapter = adapter
    }

}