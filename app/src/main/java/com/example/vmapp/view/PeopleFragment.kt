package com.example.vmapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vmapp.adapter.RecyclerViewAdapter
import com.example.vmapp.databinding.FragmentPeopleBinding
import com.example.vmapp.model.people.PeopleItem
import com.example.vmapp.util.UIState
import com.example.vmapp.viewmodel.FragmentsViewModel





class PeopleFragment : FragmentsViewModel() {



lateinit var  peopleAdapter: RecyclerViewAdapter

 private val binding : FragmentPeopleBinding by lazy{
        FragmentPeopleBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        peopleAdapter = RecyclerViewAdapter()
        binding.rvPeople.adapter = peopleAdapter
        configureObserver()
        return binding.root
    }
    private fun configureObserver() {
        viewModel.peopleList.observe(viewLifecycleOwner) { state ->

            when (state) {
                is UIState.Success<*> -> {
                    binding.apply {
                        progress.visibility = View.GONE

                        val people = state.response

                        peopleAdapter.setLists(people as List<PeopleItem>)

                    }
                }
                is UIState.Error -> {
                    binding.apply {
                        progress.visibility = View.GONE
                        tvError.text = state.exception.message
                    }
                }
                else -> {}
            }
        }
    }

}