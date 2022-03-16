package com.kwekboss.allnews.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kwekboss.allnews.ui.home.HomeViewModel

class ViewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}