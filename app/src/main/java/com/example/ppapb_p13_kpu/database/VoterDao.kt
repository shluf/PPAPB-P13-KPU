package com.example.ppapb_p13_kpu.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface VoterDao {

    @Query("SELECT * FROM voter_table ORDER BY name ASC")
    fun getAllVoters(): List<Voter>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(voter: Voter)

    @Query("SELECT * FROM voter_table WHERE id = :voterId")
    suspend fun getVoterById(voterId: Int): Voter?

    @Update
    suspend fun update(voter: Voter)

    @Delete
    suspend fun delete(voter: Voter)
}
