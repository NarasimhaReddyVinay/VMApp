package com.example.vmapp.viewmodel

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.activityViewModels

@AndroidEntryPoint
open class FragmentsViewModel: Fragment(){
    protected val viewModel :ViewModel by activityViewModels()
}