package com.jonasthuresson.onealarmclock.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout(), container, false)
    }

    abstract fun layout(): Int
    fun <T : ViewModel> getViewModel(c: Class<T>): T = getViewModel(c, requireActivity())
    fun <T : ViewModel> getViewModel(c: Class<T>, owner: ViewModelStoreOwner): T {
        return ViewModelProvider(owner, viewModelFactory)[c]
    }
}