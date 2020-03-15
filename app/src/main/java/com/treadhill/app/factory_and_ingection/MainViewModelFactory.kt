package com.treadhill.app.factory_and_ingection

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.treadhill.app.viewModel.MainViewModel

class MainViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(context) as T
    }

}