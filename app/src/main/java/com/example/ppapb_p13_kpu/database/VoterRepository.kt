package com.example.ppapb_p13_kpu.database

import kotlinx.coroutines.flow.Flow

class VoterRepository(private val voterDao: VoterDao) {

    // Mendapatkan semua data voter
    val allVoters: Flow<List<Voter>> = voterDao.getAllVoters()

    // Menambahkan data voter baru
    suspend fun insert(voter: Voter) {
        voterDao.insert(voter)
    }

    // Memperbarui data voter yang ada
    suspend fun update(voter: Voter) {
        voterDao.update(voter)
    }

    // Menghapus data voter tertentu
    suspend fun delete(voter: Voter) {
        voterDao.delete(voter)
    }
}
