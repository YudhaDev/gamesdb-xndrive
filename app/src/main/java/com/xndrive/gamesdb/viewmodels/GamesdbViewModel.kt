package com.xndrive.gamesdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.xndrive.gamesdb.models.room_entities.UserBiodataEntity
import com.xndrive.gamesdb.models.room_repositories.GamesdbRepository
import kotlinx.coroutines.launch

class GamesdbViewModel(private val repository: GamesdbRepository):ViewModel() {

    var userBiodata : LiveData<List<UserBiodataEntity>>? = null

    fun insert(userBiodataEntity: UserBiodataEntity) = viewModelScope.launch{
        repository.insertUserBiodata(userBiodataEntity)
    }
    fun getUserBiodata(user_id : Int): LiveData<List<UserBiodataEntity>> =
        repository.getUserBiodata(user_id).asLiveData()
    fun getAllUser() = repository.getAllUser().asLiveData()
}

