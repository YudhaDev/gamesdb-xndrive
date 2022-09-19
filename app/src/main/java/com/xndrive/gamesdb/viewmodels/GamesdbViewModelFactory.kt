package com.xndrive.gamesdb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xndrive.gamesdb.models.room_repositories.GamesdbRepository


class GamesdbViewModelFactory(private val repository: GamesdbRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GamesdbViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return GamesdbViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
//        return super.create(modelClass)
    }
}