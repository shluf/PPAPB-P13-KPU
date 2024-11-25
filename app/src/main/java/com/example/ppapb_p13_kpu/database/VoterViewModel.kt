package com.example.ppapb_p13_kpu.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow

class VoterViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: VoterRepository
    val allVoters: Flow<List<Voter>>

    init {
        val voterDao = VoterRoomDatabase.getDatabase(application).voterDao()
        repository = VoterRepository(voterDao)
        allVoters = repository.allVoters
    }

    suspend fun insert(voter: Voter) {
        repository.insert(voter)
    }

    suspend fun update(voter: Voter) {
        repository.update(voter)
    }

    suspend fun delete(voter: Voter) {
        repository.delete(voter)
    }
}